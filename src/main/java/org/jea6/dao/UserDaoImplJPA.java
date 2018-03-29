package org.jea6.dao;

import org.jea6.domain.User;
import org.jea6.qualifiers.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless @JPA
public class UserDaoImplJPA implements UserDao, Serializable {


    EntityManager em;

    /**
     * This method is used get all users in the kwetter application from the JPA database
     * @return A list of user objects
     */
    @Override
    public List<User> getAll() {
        Query q = em.createQuery("SELECT u FROM User u", User.class);
        return q.getResultList();
    }

    /**
     * This method is used get a specific user by ID From the JPA database
     * @param userId  The id of the you user you wish to get
     * @return an User object with the corresponding ID
     */
    @Override
    public User getUser(int userId) {
        Query q = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        q.setParameter("id", userId);
        try {
            return (User) q.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }

    /**
     * This method is used to check in the JPA Databse if the username and password combo is correct (login)
     * @param username The username of the user
     * @param password The password of the user
     * @return The user who is logged in
     */
    @Override
    public User login(String username, String password) {
        Query q = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        try {
            return (User) q.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }

    /**
     * This method is used to save the user in the JPA database
     * @param user The user you want to save to database
     */
    @Override
    public void update(User user) {
        this.em.merge(user);
    }

    /**
     * This method is used to create mock data in JPA database (YOU CANNOT CREATE A USER)
     * @param user The user
     */
    @Override
    public void create(User user) {
        this.em.persist(user);
    }

    /**
     * This function is used to delete the user
     * @param user the user you want to delete
     */
    @Override
    public void delete(User user) {
        this.em.remove(user);
    }

    /**
     * In this method the specified user gets followed and the user gets updated in the JPA Database
     * @param userSelf User object of the user who is going to follow someone
     * @param userToFollow User object of the user who is going to be followed
     */
    @Override
    public void addFollower(User userSelf, User userToFollow) {
        userSelf.follow(userToFollow);
        this.em.merge(userSelf);
    }

    @Override
    public void addFollowing(User userSelf, User userToFollow) {
        ///JPA USE 1 table with all followers
    }

    /**
     * This method is used get all followers of a user from the JPA Database
     * @param user  The user from witch you want to get the followers
     * @return a list of user objects the user follows
     */
    @Override
    public List<User> getFollowers(User user) {
        return user.followers();
    }

    /**
     * This method is used get all the users who follow the specified user
     * @param user The specified user
     * @return A list of user objects who follow the specified user
     */
    @Override
    public List<User> getFollowing(User user) {
        Query q = em.createNativeQuery("SELECT u.* FROM `user_user` as uu " +
                "JOIN `user` as u ON u.id = uu.User_ID " +
                "WHERE uu.followers_ID = " + user.getId(), User.class);
        return q.getResultList();
    }

    /**
     * This method is used for Unit test to initialize the JPA EntityManager
     * @param em The EntityManager to initialize
     */
    public void setEm(EntityManager em){
        this.em = em;
    }
}

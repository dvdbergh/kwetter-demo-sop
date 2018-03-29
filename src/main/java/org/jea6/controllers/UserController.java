package org.jea6.controllers;

import org.jea6.dao.UserDao;
import org.jea6.domain.Role;
import org.jea6.domain.User;
import org.jea6.qualifiers.JPA;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserController implements Serializable{

    @Inject
    UserDao dao;

    @Inject
    SessionController sessionController;

    /**
     * This method is used get all users in the kwetter application
     * @return A list of user objects
     */
    public List<User> getAll() {
        return dao.getAll();
    }

    /**
     * This method is used get a specific user by ID
     * @param userId  The id of the you user you wish to get
     * @return an User object with the corresponding ID
     */
    public User getUser(int userId) {
        return dao.getUser(userId);
    }

    /**
     * This method is used to change the username of a user.
     * @param username  The username you wish to assign
     */
    public void updateUsername(String username) {
        User user = sessionController.getSessionUser();
        if(user != null){
            user.setUsername(username);
            dao.update(user);
        }
    }

    /**
     * This method is used to change the role of a user. It will also check if
     * the user is an Administrator only admins can change a role
     * @param userId The ID of the user you wish to update
     * @param role  The name of the role you wish to assign
     */
    public void updateRole(int userId, String role) {
        User sessionUser = sessionController.getSessionUser();

        // Check if users are not null and if current user is admin
        if(sessionUser != null && sessionUser.getRole().equals(Role.Administrator)) {
            User user = this.getUser(userId);
            Role newRole = Role.valueOf(role);
            if (user != null && newRole != null) {
                user.setRole(newRole);
                dao.update(user);
            }
        }
    }

    /**
     * This method is used get all followers from the logged in user
     * @return a list of user objects the logged in user follows
     */
    public List<User> getFollowers(){
        User user = sessionController.getSessionUser();
        if(user != null){
            return dao.getFollowers(user);
        }
        return null;
    }

    /**
     * This method is used get all followers from a specific user.
     * @param userId  The id of the user from witch you want to get the followers
     * @return a list of user objects the user follows
     */
    public List<User> getFollowers(int userId){
        User user = this.getUser(userId);
        if(user != null){
            return dao.getFollowers(user);
        }
        return null;
    }

    /**
     * This method is used get all the users who follow you.
     * @return a list of user objects who follow the logged in user
     */
    public List<User> getFollowing(){
        User user = sessionController.getSessionUser();
        if(user != null){
            return dao.getFollowing(user);
        }
        return null;
    }

    /**
     * This method is used get all the users who follow the specified user
     * @param userId The id of the user you want to get the following of
     * @return a list of user objects who follow the specified user
     */
    public List<User> getFollowing(int userId){
        User user = this.getUser(userId);
        if(user != null){
            return dao.getFollowing(user);
        }
        return null;
    }


    /**
     * This method is used follow a user as the logged in user.
     * So if i log in with user 1 i can follow user 2
     * Also the function checks you cannot follow yourself
     * @param userId The id of the user you want to follow
     */
    public void follow(int userId) {
        User user = sessionController.getSessionUser();
        User otherUser = this.getUser(userId);
        if(user != null && otherUser != null && user.getId() != otherUser.getId()){
            dao.addFollower(user, otherUser);
            dao.addFollowing(otherUser, user);
        }
    }
}

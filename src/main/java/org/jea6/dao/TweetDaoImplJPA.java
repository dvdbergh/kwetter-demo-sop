package org.jea6.dao;

import org.jea6.domain.Tweet;
import org.jea6.domain.User;
import org.jea6.qualifiers.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@JPA
public class TweetDaoImplJPA implements TweetDao {

    @PersistenceContext(unitName = "kwetterPU")
    EntityManager em;

    /**
     * This method is used get a tweet from the JPA database by its ID
     * @param id  The id of the tweet from witch you want to get from the database
     * @return A Tweet object with corresponding ID
     */
    @Override
    public Tweet getTweet(int id) {
        Query q = em.createQuery("SELECT t FROM Tweet t WHERE t.id = :id", Tweet.class);
        q.setParameter("id", id);
        try {
            return (Tweet) q.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }

    /**
     * This method is used get all tweets from the user and the people the user follows (the Timeline)
     * Al tweets are sorted on descending order of date (newest first)
     * @param user The user from witch you want to get the timeline
     * @return a list of tweet objects who are going te be displayed on the timeline in the correct order
     */
    @Override
    public List<Tweet> getTimeline(User user) {

        Query q = em.createNativeQuery("SELECT t.* FROM `tweet` as t " +
                "LEFT JOIN `user_user` as uu ON uu.User_ID = '"+ user.getId() +"' " +
                "WHERE t.USER_ID = '"+ user.getId() +"' OR t.USER_ID = uu.followers_ID " +
                "GROUP BY t.ID " +
                "ORDER BY t.DATETIME DESC", Tweet.class);
        return q.getResultList();
    }

    /**
     * This method is used get the first 10 tweets from the user
     * Al tweets are sorted on descending order of date (newest first)
     * @param user The user from witch you want to get the timeline
     * @return A list of 10 or less tweet objects in the correct order
     */
    @Override
    public List<Tweet> getRecents(User user) {
        Query q = em.createQuery("SELECT t FROM Tweet t WHERE t.user = :user ORDER BY t.datetime DESC", Tweet.class);
        q.setParameter("user", user);
        q.setMaxResults(10);
        return q.getResultList();
    }

    /**
     * This method is used to search for tweets on the tweet text (body)
     * @param search  The message of the tweet (body) you want to use for the search
     * @return A list of tweet objects who match the search criteria
     */
    @Override
    public List<Tweet> searchTweets(String search) {
        Query q = em.createQuery("SELECT t FROM Tweet t WHERE t.text LIKE :text", Tweet.class);
        q.setParameter("text", "%" + search + "%");
        return q.getResultList();
    }

    /**
     * This method is used to delete a tweet in the JPA database
     * @param tweet The tweet you want to delete
     */
    @Override
    public void delete(Tweet tweet) {
        em.remove(tweet);
    }

    /**
     * This method is used to create a tweet in the JPA database
     * @param tweet The tweet you want to save
     */
    @Override
    public void create(Tweet tweet) {
        em.persist(tweet);
    }

    /**
     * This funtion is used to update a tweet with primary key
     * @param tweet the tweet you want to update
     */
    @Override
    public void update(Tweet tweet) {
        em.merge(tweet);
    }

    /**
     * This method is used for Unit test to initialize the JPA EntityManager
     * @param em The EntityManager to initialize
     */
    public void setEm(EntityManager em){
        this.em = em;
    }
}

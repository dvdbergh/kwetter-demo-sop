package org.jea6.controllers;

import org.jea6.dao.TweetDao;
import org.jea6.domain.Role;
import org.jea6.domain.Tweet;
import org.jea6.domain.User;
import org.jea6.qualifiers.JPA;

import javax.inject.Inject;
import java.util.List;

public class TweetController {

    @Inject
    TweetDao dao;

    @Inject
    UserController userController;

    @Inject
    SessionController sessionController;

    /**
     This method is used get all tweets on the timeline for a specified user
     @param userId  The id of the user from witch you want to get the timeline
     @return a list of tweet objects who are going te be displayed on the timeline
     */
    public List<Tweet> getTimeline(int userId) {
        User user = userController.getUser(userId);
        return dao.getTimeline(user);
    }

    /**
     This method is used to get the first 10 tweets of the specified user
     @param userId  The id of the user from witch you want to get the tweets
     @return a list of 10 tweet objects
    */
    public List<Tweet> getRecents(int userId) {
        User user = userController.getUser(userId);
        if(user != null){
            return dao.getRecents(user);
        }
        return null;
    }

    /**
     This method is used get all tweets on the timeline for the logged in user
     @return a list of tweet objects who are going te be displayed on the timeline
     */
    public List<Tweet> getTimeline() {
        User user = sessionController.getSessionUser();
        return dao.getTimeline(user);
    }

    /**
     This method is used to get the first 10 tweets of the logged in user
     @return a list of 10 tweet objects
     */
    public List<Tweet> getRecents() {
        User user = sessionController.getSessionUser();
        return dao.getRecents(user);
    }

    /**
     This method is used to delete a tweet
     This method checks if you are a moderator (only them can delete tweet)
     @param tweetId  The ID of the tweet you want to delete
    */
    public void delete(int tweetId) {
        Tweet tweet = dao.getTweet(tweetId);
        User user = sessionController.getSessionUser();
        if(tweet != null && user != null && user.getRole().equals(Role.Moderator)){
            dao.delete(tweet);
        }
    }

    /**
    This method is used to create a tweet
    @param text  The message of the tweet (body)
    @param userId  The id of the user who creates the tweet
    */
    public Tweet create(String text, int userId) {
        User user = userController.getUser(userId);
        Tweet tweet = new Tweet(text, user);
        dao.create(tweet);
        return tweet;
    }

    /**
     This method is used to search for tweets on the tweet text (body)
     @param text  The message of the tweet (body) you want to use for the search
     @return A list of tweet objects who match the search criteria
    */
    public List<Tweet> searchTweets(String text) {
        return dao.searchTweets(text);
    }
}

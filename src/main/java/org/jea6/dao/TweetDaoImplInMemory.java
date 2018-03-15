package org.jea6.dao;

import org.jea6.domain.Tweet;
import org.jea6.domain.User;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

import static java.lang.System.out;

@Stateless
@Singleton
public class TweetDaoImplInMemory implements TweetDao {

    List<Tweet> tweets = new ArrayList<>();

    //@PersistenceContext(unitName="kwetterUnit")
    //public EntityManager em;
    @Inject
    UserDao userDao;


    @Override
    public Tweet getTweet(int id) {
        return tweets.get(id);
    }

    @Override
    public List<Tweet> getTimeline(User user) {
        List<Tweet> timeLine = new ArrayList<>();
        //Get all following for current user
        List<User> following = userDao.getFollowing(user);

        //Loop through all tweets
        for(Tweet t : this.tweets){
            //check is tweet is current user
            if(t.getUser().getId() == user.getId()){
                timeLine.add(t);
                continue;
            }

            //Loop trough all following
            for(User u : following){
                ///Tweet is from a follower
                if(t.getUser().getId() == u.getId()) {
                    timeLine.add(t);
                    break;
                }
            }
        }
        return timeLine;
    }

    @Override
    public List<Tweet> getRecents(User user) {
        List<Tweet> recents = new ArrayList<>();

        // Loop trought all tweets to get the first 10
        for(Tweet t : this.tweets){
            if(t.getUser().getId() == user.getId()){
                //IF list contains 10 items stop while loop
                if(recents.size() >= 10){
                    break;
                }
                recents.add(t);
            }
        }

        return recents;
    }

    @Override
    public List<Tweet> searchTweets(String search) {
        List<Tweet> foundTweets = new ArrayList<>();
        for(Tweet tweet : tweets){
            if (tweet.getText().toLowerCase().contains(search.toLowerCase())){
                foundTweets.add(tweet);
            }
        }
        return foundTweets;
    }

    @Override
    public void delete(Tweet tweet) {
        tweets.remove(tweet.getId());
    }

    @Override
    public void create(Tweet tweet) {
        tweet.setId(tweets.size() + 1);
        tweets.add(tweet);

        //Sort Tweets on date (So are the first 10 of the list the recent)
        Collections.sort(tweets, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o2.getDatetime().compareTo(o1.getDatetime()); // sort newest date first
            }
        });
    }

    @Override
    public void update(Tweet tweet) {

    }
}

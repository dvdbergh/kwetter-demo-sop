package org.jea6.dao;

import org.jea6.domain.Tweet;
import org.jea6.domain.User;

import java.util.List;

public interface TweetDao {
    Tweet getTweet(int id);
    List<Tweet> getTimeline(User user);
    List<Tweet> getRecents(User user);
    List<Tweet> searchTweets(String search);
    void delete(Tweet tweet);
    void create(Tweet tweet);
    void update(Tweet tweet);
}

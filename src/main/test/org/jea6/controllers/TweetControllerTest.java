package org.jea6.controllers;

import org.jea6.dao.TweetDao;
import org.jea6.dao.TweetDaoImplJPA;
import org.jea6.dao.UserDaoImplJPA;
import org.jea6.domain.Role;
import org.jea6.domain.Tweet;
import org.jea6.domain.User;
import org.jea6.mocking.DaoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.constraints.AssertTrue;
import java.util.List;

public class TweetControllerTest {

    TweetController tweetController;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPUTest");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDaoImplJPA tweetDao;
    private UserDaoImplJPA userDao;

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tweetDao = new TweetDaoImplJPA();
        tweetDao.setEm(em);

        userDao = new UserDaoImplJPA();
        userDao.setEm(em);


        tweetController = new TweetController();

        UserController userController = new UserController();
        userController.dao = userDao;


        tweetController.dao = tweetDao;
        tweetController.userController = userController;

    }

    @Test
    public void testCreate(){
       Tweet t = tweetController.create("testtweet", 1);
    }

    @Test
    public void testTimeline(){
        List<Tweet> tweets = tweetController.getTimeline(1);
        Assert.assertTrue(tweets.size() >= 2);
    }

    @Test
    public void testRecent(){
        List<Tweet> tweets = tweetController.getRecents(1);
        Assert.assertTrue(tweets.size() >= 1);
    }

    @Test
    public void testSearch(){
        List<Tweet> tweets = tweetController.searchTweets("test");
        Assert.assertTrue(tweets.size() >= 1);
    }
}

package org.jea6.dao;

import org.jea6.Utils.DatabaseCleaner;
import org.jea6.domain.Role;
import org.jea6.domain.Tweet;
import org.jea6.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class TweetDaoImplTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPUTest");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDaoImplJPA tweetDao;
    private UserDaoImplJPA userDao;

    private User user1;
    private Tweet tweet1;


    @Before
    public void setUp() {
        /*try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(TweetDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tweetDao = new TweetDaoImplJPA();
        tweetDao.setEm(em);

        userDao = new UserDaoImplJPA();
        userDao.setEm(em);

        user1 = new User("dennis", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator);
        user1.setId(1);

        tweet1 = new Tweet("tekst van de tweet", user1);

        userDao.create(user1);
        tweetDao.create(tweet1);
    }

    public void getTweetTestNoResult() {
        Tweet result = tweetDao.getTweet(-500);
        assertNull(result);
    }

    @Test
    public void getTweetTest() {
        Tweet result = tweetDao.getTweet(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void createTest(){
        tweetDao.create(tweet1);
    }

    @Test
    public void getTimeline() {

        List<Tweet> results = tweetDao.getTimeline(user1);

        assertTrue(results.size() >= 1);

    }

    @Test
    public void getRecent() {

        List<Tweet> results = tweetDao.getRecents(user1);
        assertTrue(results.size() >= 1);

    }
}

package org.jea6.dao;

import org.jea6.domain.Role;
import org.jea6.domain.Tweet;
import org.jea6.domain.User;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDaoImplTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPUTest");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDaoImplJPA tweetDao;
    private UserDaoImplJPA userDao;

    private User user1;
    private Tweet tweet1;


    @Before
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tweetDao = new TweetDaoImplJPA();
        tweetDao.setEm(em);

        userDao = new UserDaoImplJPA();
        userDao.setEm(em);

        user1 = new User("dennis", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator);
        //user1.setId(1);

        tweet1 = new Tweet("tekst van de tweet", user1);

        userDao.create(user1);
        tweetDao.create(tweet1);
    }

    @Test
    public void getUserTest() {
        User result = userDao.getUser(1);

        assertEquals(1, result.getId());
    }

    @Test
    public void getAllTest() {
        List<User> results = userDao.getAll();

        assertTrue(results.size() >= 1);
    }

    @Test
    public void updateTest() {
        User user = userDao.getUser(1);
        String email = "test123@blablablal.test";
        String username = "nieuweusername";
        user.setEmail(email);
        user.setUsername(username);

        userDao.update(user);
        User result = userDao.getUser(1);
        assertEquals(email, result.getEmail());
        assertEquals(username, result.getUsername());

        ///Test another time update on same user
        user = userDao.getUser(1);
        email = "email@gmail.com";
        username = "username";
        user.setEmail(email);
        user.setUsername(username);

        userDao.update(user);
        result = userDao.getUser(1);
        assertEquals(email, result.getEmail());
        assertEquals(username, result.getUsername());

    }
}

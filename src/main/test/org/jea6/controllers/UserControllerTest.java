package org.jea6.controllers;

import org.jea6.dao.TweetDaoImplJPA;
import org.jea6.dao.UserDaoImplJPA;
import org.jea6.domain.Tweet;
import org.jea6.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class UserControllerTest {

    UserController userController;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPUTest");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDaoImplJPA tweetDao;
    private UserDaoImplJPA userDao;

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoImplJPA();
        userDao.setEm(em);

        userController = new UserController();
        userController.dao = userDao;
    }

    @Test
    public void getAllTest(){
        List<User> users = userController.getAll();
        Assert.assertTrue(users.size() >= 0);
    }

    @Test
    public void getUserTest(){
        User user = userController.getUser(1);
        Assert.assertEquals(user.getId(), 1);
    }

    @Test
    public void getFollowersTest(){
        List<User> users = userController.getFollowers(1);
        Assert.assertTrue(users.size() >= 1);
    }

    @Test
    public void getFollowingTest(){
        List<User> users = userController.getFollowing(2);
        Assert.assertTrue(users.size() >= 1);
    }
}

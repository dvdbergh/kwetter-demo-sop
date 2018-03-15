package org.jea6.domain;

import org.jea6.dao.TweetDaoImplInMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;

@Stateless
public class TweetTest {
    User user1;
    Tweet t1;

    @Before
    public void init(){
        t1 = new Tweet();
        user1 = new User("dennis", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator);
    }

    @Test
    public void testConstructor(){
        String text = "test123";

        Tweet t = new Tweet(text, user1);

        Assert.assertEquals(text, t.getText());
        Assert.assertEquals(user1, t.getUser());
    }

    @Test
    public void testText(){
        t1.setText("test1");
        Assert.assertEquals("test1", t1.getText());

        t1.setText("test2");
        Assert.assertEquals("test2", t1.getText());
    }

    @Test
    public void testDate(){
        Date d = new Date();
        t1.setDatetime(d);
        Assert.assertEquals(d, t1.getDatetime());
    }

    @Test
    public void testId(){
        t1.setId(500);
        Assert.assertEquals(500, t1.getId());

        t1.setId(55);
        Assert.assertEquals(55, t1.getId());
    }

    @Test
    public void testUser(){
        t1.setUser(user1);
        Assert.assertEquals(user1, t1.getUser());

        User user2 = new User("dennis", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator);
        t1.setUser(user2);
        Assert.assertEquals(user2, t1.getUser());
    }

    @Test
    public void testFollow(){
        User user2 = new User("test2", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator);
        User user3 = new User("test3", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator);

        user1.follow(user2);
        Assert.assertEquals(1, user1.followers().size());

        user1.follow(user3);
        Assert.assertEquals(2, user1.followers().size());

        user1.follow(user3);
        Assert.assertEquals(2, user1.followers().size());
    }
}

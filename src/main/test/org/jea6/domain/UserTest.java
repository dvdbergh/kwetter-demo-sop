package org.jea6.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    User user1;
    Tweet t1;

    @Before
    public void init(){
        t1 = new Tweet();
        user1 = new User("dennis", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator);
    }

    @Test
    public void testConstructor(){
        String username = "test123";
        String pass = "test123";
        String bio = "test123";
        String location = "test123";
        String website = "test123";
        String email = "test123";
        Role role = Role.Administrator;

        User u = new User(username, pass, bio, location, website, email, role);

        Assert.assertEquals(username, u.getUsername());
        Assert.assertEquals(pass, u.getPassword());
        Assert.assertEquals(bio, u.getBio());
        Assert.assertEquals(location, u.getLocation());
        Assert.assertEquals(website, u.getWebsite());
        Assert.assertEquals(email, u.getEmail());
        Assert.assertEquals(role, u.getRole());
        Assert.assertEquals(0, u.followers().size());
        Assert.assertEquals(0, u.following().size());
    }

    @Test
    public void testUsername(){
        String username = "blalbllablab";
        user1.setUsername(username);
        Assert.assertEquals(username, user1.getUsername());
    }
    @Test
    public void testPassword(){
        String password = "geheim";
        user1.setPassword(password);
        Assert.assertEquals(password, user1.getPassword());
    }
    @Test
    public void testBio(){
        String bio = "mijn geweldige bio";
        user1.setBio(bio);
        Assert.assertEquals(bio, user1.getBio());
    }
    @Test
    public void testLocation(){
        String location = "Veghel";
        user1.setLocation(location);
        Assert.assertEquals(location, user1.getLocation());
    }
    @Test
    public void testWebsite(){
        String username = "www.google.nl";
        user1.setWebsite(username);
        Assert.assertEquals(username, user1.getWebsite());
    }
    @Test
    public void testEmail(){
        String username = "test@gmail.com";
        user1.setEmail(username);
        Assert.assertEquals(username, user1.getEmail());
    }
    @Test
    public void TestRole(){
        Role role = Role.Administrator;
        user1.setRole(role);
        Assert.assertEquals(role, user1.getRole());
    }
}

package org.jea6.services;

import com.sun.deploy.util.SessionState;
import org.jea6.domain.Role;
import org.jea6.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class UserServiceTest {

    private Client client;
    private WebTarget target;
    static final String PATH = "/kwetter/webapi/users/";
    static final String BASEURL = "http://localhost:8080" + PATH;
    static final String MEDIATYPE = MediaType.APPLICATION_JSON;

    @Before
    public void init(){
        client = ClientBuilder.newClient();
        target = client.target(BASEURL);
    }


    @Test
    public void testGetAll(){
        List<User> users = this.target.request(MEDIATYPE).get(List.class);
        Assert.assertEquals(users.size(), 3);
    }

    @Test
    public void testGetUser(){
        User user = this.target.path("1").request(MEDIATYPE).get(User.class);
        Assert.assertEquals(user.getId(), 1);
    }

    @Test
    public void testGetFollowers(){
        List<User> users = this.target.path("/1/followers").request(MEDIATYPE).get(List.class);
        Assert.assertTrue(users.size() >= 0);
    }

    @Test
    public void testGetFollowing(){
        List<User> users = this.target.path("/1/following").request(MEDIATYPE).get(List.class);
        Assert.assertTrue(users.size() >= 0);
    }

}

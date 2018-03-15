package org.jea6.services;

import org.jea6.domain.Role;
import org.jea6.domain.Tweet;
import org.jea6.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class TweetServiceTest {

    private Client client;
    private WebTarget target;
    static final String PATH = "/kwetter/webapi/tweets/";
    static final String BASEURL = "http://localhost:8080" + PATH;
    static final String MEDIATYPE = MediaType.APPLICATION_JSON;

    @Before
    public void init(){
        client = ClientBuilder.newClient();
        target = client.target(BASEURL);
    }

    @Test
    public void testCreateTweet(){
        Form form = new Form();
        form.param("text", "dit is een tweet vanuit de unit test")
                .param("userId", "1");

        Entity<Form> entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);
        Tweet result = this.target.request(MEDIATYPE).post(entity, Tweet.class);
        Assert.assertNotNull(result);
    }

    @Test
    public void testTimeline(){
        List<Tweet> tweets = this.target.path("timeline/1").request(MEDIATYPE).get(List.class);
        Assert.assertTrue(tweets.size() >= 0);
    }

    @Test
    public void testRecent(){
        List<Tweet> tweets = this.target.path("recent/1").request(MEDIATYPE).get(List.class);
        Assert.assertTrue(tweets.size() >= 0);
    }

    @Test
    public void testRecentUserNotExists(){
        List<Tweet> tweets = this.target.path("recent/123").request(MEDIATYPE).get(List.class);
        Assert.assertNull(tweets);
    }
}

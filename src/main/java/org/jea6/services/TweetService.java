package org.jea6.services;

import io.swagger.annotations.Api;
import org.jea6.controllers.TweetController;
import org.jea6.domain.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tweets") @Api @Stateless
public class TweetService {

    @Inject
    TweetController tweetController;

    @Path("/timeline/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> getTimeline(@PathParam("userId") int userId) {
        return tweetController.getTimeline(userId);
    }

    @Path("/recent/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> getRecents(@PathParam("userId") int userId) {
        return tweetController.getRecents(userId);
    }

    @Path("/{tweetId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public int delete(@PathParam("tweetId") int tweetId) {
        tweetController.delete(tweetId);
        return tweetId;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tweet create(@FormParam("text") String text, @FormParam("userId") int userId) {
        return tweetController.create(text, userId);
    }

    @Path("/search/{text}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> searchTweets(@PathParam("text") String text) {
        return tweetController.searchTweets(text);
    }


}

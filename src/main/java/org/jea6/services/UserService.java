package org.jea6.services;

import io.swagger.annotations.Api;
import org.jea6.controllers.UserController;
import org.jea6.domain.User;
import org.jea6.qualifiers.JPA;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users") @Api @Stateless
public class UserService {

    @Inject
    UserController userController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userController.getAll();
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") int userId) {
        return userController.getUser(userId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateUsername(@FormParam("username") String username) {
        userController.updateUsername(username);
    }

    @Path("/{userId}/role")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateRole(@PathParam("userId") int userId, @FormParam("role") String role) {
        userController.updateRole(userId, role);
    }

    @Path("/follow/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void follow(@PathParam("userId") int userId) {
        userController.follow(userId);

    }

    @Path("/following")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getFollowing() {
        return userController.getFollowing();
    }

    @Path("/{userId}/following")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getFollowing(@PathParam("userId") int userId) {
        return userController.getFollowing(userId);
    }


    @Path("/followers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getFollowers() {
        return userController.getFollowers();
    }

    @Path("/{userId}/followers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getFollowers(@PathParam("userId") int userId) {
        return userController.getFollowers(userId);
    }
}

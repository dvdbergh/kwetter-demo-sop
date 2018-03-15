package org.jea6.services;


import io.swagger.annotations.Api;
import org.jea6.controllers.SessionController;
import org.jea6.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/session") @Api @Stateless
public class SessionService {

    @Inject
    SessionController sessionController;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean login(@FormParam("username") String username, @FormParam("password") String password) {
        return sessionController.login(username, password);
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean logout() {
        sessionController.logout();
        return true;
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSessionUser() {

        return sessionController.getSessionUser();
    }
}

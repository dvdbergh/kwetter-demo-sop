package org.jea6.jsp;

import org.jea6.controllers.SessionController;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named(value = "sessionJsp")
public class SessionJsp {

    @Inject
    private SessionController sessionController;

    private String username;
    private String password;

    public void login(){
        sessionController.login("dennis", "pass");
    }

}

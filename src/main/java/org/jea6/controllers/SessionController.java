package org.jea6.controllers;

import org.jea6.dao.UserDao;
import org.jea6.domain.User;
import org.jea6.qualifiers.JPA;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@Stateless
public class SessionController {

    @Inject
    UserDao dao;

    private User currentUser;

    /**
     This method is used to log in a user
     @param username  The username of the user
     @param password  The password of the user
     @return True if login is correct, false if login is username/password combo is incorrect
     */
    public boolean login(String username, String password) {
        currentUser = dao.login(username, password);
        return currentUser != null;
    }

     /**
        This method is used to logout the currently logged in user
     */
    public void logout() {
        currentUser = null;
    }

    /**
     This method is used to get the currently logged in user
     @return A user object of the currently logged in user
     */
    public User getSessionUser() {
        return currentUser;
    }
}

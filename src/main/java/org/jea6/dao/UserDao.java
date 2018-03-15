package org.jea6.dao;

import org.jea6.domain.User;

import java.io.Serializable;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getUser(int userId);

    User login(String username, String password);

    void update(User user);

    void create(User user);

    void delete(User user);

    void addFollower(User userSelf, User userToFollow);

    void addFollowing(User userSelf, User userToFollow);

    List<User> getFollowers(User user);

    List<User> getFollowing(User user);
}

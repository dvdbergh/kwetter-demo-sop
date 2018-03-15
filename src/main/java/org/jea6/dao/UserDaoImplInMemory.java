package org.jea6.dao;

import org.jea6.domain.Role;
import org.jea6.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Stateless @Singleton @Default
public class UserDaoImplInMemory implements UserDao {

    HashMap<Integer, User> userss = new HashMap<>();
    HashMap<Integer, List<Integer>> followers = new HashMap<>();
    HashMap<Integer, List<Integer>> following = new HashMap<>();

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(userss.values());
    }

    @Override
    public User getUser(int userId) {
        return userss.get(userId);
    }

    @Override
    public User login(String username, String password) {
        for(User user : userss.values()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(User user) {

    }

    public void create(User user){
        user.setId(userss.size() + 1);
        userss.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void addFollower(User userSelf, User userToFollow) {
        int key = userSelf.getId();
        int value = userToFollow.getId();
        if (followers.get(key) == null) {
            followers.put(key, new ArrayList<Integer>());
        }
        if(followers.get(key).contains(value) == false) {
            followers.get(key).add(value);
        }
    }

    @Override
    public void addFollowing(User userSelf, User userToFollowing) {
        int key = userSelf.getId();
        int value = userToFollowing.getId();
        if (following.get(key) == null) {
            following.put(key, new ArrayList<Integer>());
        }
        if(following.get(key).contains(value) == false) {
            following.get(key).add(value);
        }
    }

    @Override
    public List<User> getFollowers(User user) {
        List<User> users = new ArrayList<>();
        List<Integer> data = following.get(user.getId());
        if(data != null) {
            for (Integer userId : data) {
                users.add(this.getUser(userId));
            }
        }
        return users;
    }

    @Override
    public List<User> getFollowing(User user) {
        List<User> users = new ArrayList<>();
        List<Integer> data = followers.get(user.getId());
        if(data != null) {
            for (Integer userId : data) {
                users.add(this.getUser(userId));
            }
        }
        return users;
    }
}

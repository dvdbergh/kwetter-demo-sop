package org.jea6.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.AccessMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String username;
    private String password;
    private String bio;
    private String location;
    private String website;
    private String email;
    private Role role;

    @ManyToMany @JsonIgnore
    private List<User> followers;

    @ManyToMany
    @JoinTable(name = "user_user",
            joinColumns = @JoinColumn(name = "followers_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> following;

    /**
     * The constructor of the User class sets all fields of user class
     * It also creates 2 lists of Users to keep track of followers and following users
     * @param username username of the user
     * @param password password of the user
     * @param bio Biography of the user
     * @param location Location the user is situated
     * @param website The website of the user
     * @param email The email-address of the user
     * @param role The role of the user (can be User, Administrator or Moderator)
     */
    public User(String username, String password, String bio, String location, String website, String email, Role role) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.email = email;
        this.role = role;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    /**
     * Empty constructor is used for API calls (JSON)
     */
    public User(){

    }

    /**
     * getter of ID
     * @return ID of User
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of id
     * @param id The new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of all followers
     * Is ignored by JSON in API to prevent infinite loop
     * @return A list of all followers of this user
     */
    @JsonIgnore
    public List<User> followers() {
        return (List<User>)followers;
    }

    /**
     * This method will add a user to list of followers
     * @param user The user who is followed
     */
    public void follow(User user) {
        if(this.followers.contains(user) == false){
            this.followers.add(user);
        }
    }

    /**
     * Getter of all followers
     * Is ignored by JSON in API to prevent infinite loop
     * @return A list of all the people who follow this user
     */
    @JsonIgnore
    public List<User> following() {
        return this.following;
    }

    /**
     * getter of username
     * @return username of User
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter of username
     * @param username The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter of password
     * @return password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of password
     * @param password The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter of biography
     * @return biography of User
     */
    public String getBio() {
        return bio;
    }

    /**
     * Setter of the biography
     * @param bio the biography of the user
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * getter of location
     * @return location of User
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter of the location
     * @param location the location of the user
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getter of website
     * @return website of User
     */
    public String getWebsite() {
        return website;
    }

    /**
     * The setter of the website
     * @param website the website of the user
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Getter of the e-mail
     * @return the email-address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter of Role
     * Can be User, Administrator or Moderator
     * @return Role object the user has
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter of role
     * @param role The new role
     */
    public void setRole(Role role) {
        this.role = role;
    }
}

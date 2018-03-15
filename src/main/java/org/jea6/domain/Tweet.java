package org.jea6.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
//@Table(name = "tweets")
public class Tweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "text")
    private String text;
    private Date datetime;
    @ManyToOne
    private User user;

    /**
     * The constructor of the Tweet class sets text and user
     * Also create a new date (now) when the tweet is created
     * @param text The message of the tweet (body)
     * @param user The user who has created the tweet
     */
    public Tweet(String text, User user) {
        this.text = text;
        this.user = user;
        this.datetime = new Date();
    }

    /**
     * Empty constructor is used for API calls (JSON)
     */
    public Tweet(){

    }

    /**
     * getter of ID
     * @return ID of tweet
     */
    public int getId() {
        return id;
    }

    /**
     * Getter of text (message of tweet)
     * @return the message of the tweet
     */
    public String getText() {
        return text;
    }

    /**
     * Getter of creation dateTime of the tweet
     * @return The creation date of the tweet
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Getter of the user who created the tweet
     * @return User object of user who created the tweet
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter of id
     * @param id The new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter of the text
     * @param text Text of the tweet
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter of creation date of the tweet
     * @param datetime The date you want to be the new date
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * Setter of the user of the tweet
     * @param user user of the tweet
     */
    public void setUser(User user){
        this.user = user;
    }
}

package org.jea6.jsp;

import org.jea6.controllers.TweetController;
import org.jea6.domain.Tweet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named(value = "tweetJsp")
public class TweetJsp {

    @Inject
    private TweetController tweetController;

    public List<Tweet> getTimeline(){
        return tweetController.getTimeline(1);
    }

}

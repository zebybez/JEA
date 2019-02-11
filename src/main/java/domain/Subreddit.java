package domain;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Subreddit {
    private String uuid;
    private List<Post> posts;
    private List<User> moderators;
    private List<User> subscribers;
    private String rules;

    private Subreddit(){

    }

    public Subreddit(String uuid, List<Post> posts, List<User> moderators, List<User> subscribers, String rules) {
        this.uuid = uuid;
        this.posts = posts;
        this.moderators = moderators;
        this.subscribers = subscribers;
        this.rules = rules;
    }
}

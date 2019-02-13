package domain;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Subreddit {
    private String uuid;
    private List<Post> posts;
    private List<Profile> moderators;
    private List<Profile> subscribers;
    private String rules;

    private Subreddit(){

    }

    public Subreddit(String uuid, List<Post> posts, List<Profile> moderators, List<Profile> subscribers, String rules) {
        this.uuid = uuid;
        this.posts = posts;
        this.moderators = moderators;
        this.subscribers = subscribers;
        this.rules = rules;
    }
}

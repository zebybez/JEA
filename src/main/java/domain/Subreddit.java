package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "subreddits", schema = "SUBREDDITS")
public class Subreddit {
    @Id
    private String uuid;
    @ManyToMany(mappedBy = "moderatorOf")
    @JsonIgnore
    private List<Profile> moderators;
    @ManyToMany(mappedBy = "subscriberOf")
    @JsonIgnore
    private List<Profile> subscribers;
    @OneToMany(mappedBy = "subreddit")
    @JsonIgnore
    private List<Post> posts;
    @ManyToOne
    private Profile creator;
    private String rules;

    public Subreddit() {

    }

    public Subreddit(String uuid, List<Post> posts, List<Profile> moderators, List<Profile> subscribers, String rules) {
        this.uuid = uuid;
        this.posts = posts;
        this.moderators = moderators;
        this.subscribers = subscribers;
        this.rules = rules;
    }

    public Profile getCreator() {
        return creator;
    }

    public void setCreator(Profile creator) {
        this.creator = creator;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Profile> getModerators() {
        return moderators;
    }

    public void setModerators(List<Profile> moderators) {
        this.moderators = moderators;
    }

    public List<Profile> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Profile> subscribers) {
        this.subscribers = subscribers;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}

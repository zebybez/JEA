package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Subreddit")
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String uuid;
    @ManyToMany(mappedBy = "moderatorOf", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Profile> moderators;
    @ManyToMany(mappedBy = "subscriberOf", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Profile> subscribers;
    @OneToMany(mappedBy = "subreddit")
    private List<Post> posts;
    @ManyToOne
    private Profile creator;
    private String rules;
    @Column(unique = true)
    private String name;

    public Subreddit() {

    }

    public Subreddit(String uuid, String name, String rules) {
        this.uuid = uuid;
        this.name = name;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

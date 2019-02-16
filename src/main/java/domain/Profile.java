package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Profile {
    @Id
    private long id;

    private String uuid = UUID.randomUUID().toString();
    private int karmaPoints;

    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<Post> posts;
    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<Comment> comments;
    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<Subreddit> ownerOf;
    @ManyToMany
    @JsonIgnore
    private List<Subreddit> moderatorOf;
    @ManyToMany
    @JsonIgnore
    private List<Subreddit> subscriberOf;

    public Profile() {
    }

    public Profile(int karmaPoints, List<Subreddit> moderatorOf, List<Subreddit> subscriberOf, List<Post> posts, List<Comment> comments) {
        this.karmaPoints = karmaPoints;
        this.moderatorOf = moderatorOf;
        this.subscriberOf = subscriberOf;
        this.posts = posts;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Subreddit> getOwnerOf() {
        return ownerOf;
    }

    public void setOwnerOf(List<Subreddit> ownerOf) {
        this.ownerOf = ownerOf;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getKarmaPoints() {
        return karmaPoints;
    }

    public void setKarmaPoints(int karmaPoints) {
        this.karmaPoints = karmaPoints;
    }

    public List<Subreddit> getModeratorOf() {
        return moderatorOf;
    }

    public void setModeratorOf(List<Subreddit> moderatorOf) {
        this.moderatorOf = moderatorOf;
    }

    public List<Subreddit> getSubscriberOf() {
        return subscriberOf;
    }

    public void setSubscriberOf(List<Subreddit> subcriberOf) {
        this.subscriberOf = subcriberOf;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

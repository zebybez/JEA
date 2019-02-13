package domain;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Profile {
    private String uuid;
    private int karmaPoints;
    private List<Subreddit> moderatorOf;
    private List<Subreddit> subcriberOf;
    private List<Post> posts;
    private List<Comment> comments;
    public Profile() {
    }
    public Profile(int karmaPoints, List<Subreddit> moderatorOf, List<Subreddit> subcriberOf, List<Post> posts, List<Comment> comments) {
        this.karmaPoints = karmaPoints;
        this.moderatorOf = moderatorOf;
        this.subcriberOf = subcriberOf;
        this.posts = posts;
        this.comments = comments;
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

    public List<Subreddit> getSubcriberOf() {
        return subcriberOf;
    }

    public void setSubcriberOf(List<Subreddit> subcriberOf) {
        this.subcriberOf = subcriberOf;
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

package domain;

import domain.interfaces.Judgeable;

import javax.persistence.Entity;

@Entity
public class Comment implements Judgeable {
    private String uuid;
    private int upvotes;
    private int downvotes;
    private User creator;
    private Judgeable parent;

    public String getUuid() {
        return uuid;
    }

    public Judgeable getParent() {
        return parent;
    }

    private Comment() {

    }

    public Comment(String uuid, int upvotes, int downvotes, User creator, Judgeable parent) {
        this.uuid = uuid;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.creator = creator;
        this.parent = parent;
    }

    @Override
    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public int getDownvotes() {
        return downvotes;
    }

    @Override
    public User getCreator() {
        return creator;
    }

    @Override
    public int getScore() {
        return 0;
    }
}

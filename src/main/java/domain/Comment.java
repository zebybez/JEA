package domain;

import domain.interfaces.Judgeable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Comment implements Judgeable {
    @Id
    private String uuid;
    private int upvotes;
    private int downvotes;
    @ManyToOne
    private Profile creator;
    @ManyToOne
    private Post parent;
    @ManyToOne
    private Comment replyTo;
    @OneToMany(mappedBy = "replyTo")
    private List<Comment> replies;

    public Comment() {

    }

    public Comment(String uuid, int upvotes, int downvotes, Profile creator, Post parent) {
        this.uuid = uuid;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.creator = creator;
        this.parent = parent;
    }

    public String getUuid() {
        return uuid;
    }

    public Judgeable getParent() {
        return parent;
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
    public Profile getCreator() {
        return creator;
    }

    @Override
    public int getScore() {
        return 0;
    }
}

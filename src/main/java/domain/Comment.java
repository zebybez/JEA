package domain;

import domain.interfaces.Judgeable;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment implements Judgeable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String uuid;
    private int upvotes;
    private int downvotes;
    @ManyToOne
    private Profile creator;
    @ManyToOne
    private Post parent;
    @ManyToOne
    private Comment replyTo;
    @OneToMany(mappedBy = "replyTo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Comment getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public Post getParent() {
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

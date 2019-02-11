package domain;

import domain.interfaces.Judgeable;

public class Post implements Judgeable {

    private int upvotes;
    private int downvotes;
    private User creator;
    private String title;
    private String content;
    private Subreddit subreddit;
    private boolean isNSFW;


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

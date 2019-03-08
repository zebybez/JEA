package business.interfaces;

import domain.Profile;
import domain.Subreddit;

import java.util.List;

public interface SubredditService {
    List<Subreddit> getAllSubreddits();

    Subreddit addSubreddit(Profile profile, String name);

    Subreddit getSubredditByName(String name);
}

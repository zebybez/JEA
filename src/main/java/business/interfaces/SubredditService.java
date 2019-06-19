package business.interfaces;

import domain.Profile;
import domain.Subreddit;

import java.util.List;

public interface SubredditService {
    List<Subreddit> getAllSubreddits();

    Subreddit addSubreddit(Profile profile, String name, String rules);

    Subreddit getSubredditByName(String name);

    List<Subreddit> getSubscriptionsForUser(String userName);

    List<Subreddit> getModeratedSubs(String userName);

    Subreddit editSubreddit(String name, String rules);

}

package business.interfaces;

import domain.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getProfileList();

    Profile getProfileByName(String name);

    Profile getProfileById(long creatorId);

    List<Profile> getSubscribersOfSubreddit(String subreddit);

    List<Profile> getModeratorsOfSubreddit(String subreddit);
}

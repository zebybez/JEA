package data.interfaces;

import domain.Profile;
import domain.Subreddit;

import java.util.List;

public interface ProfileDao extends BaseDao<Profile> {
    List<Profile> getAllProfiles();

    Profile getProfileByName(String name);
}

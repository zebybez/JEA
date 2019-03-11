package business.interfaces;

import domain.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getProfileList();

    Profile getProfileByName(String name);

    Profile getProfileById(long creatorId);
}

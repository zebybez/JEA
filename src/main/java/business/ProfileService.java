package business;

import data.interfaces.ProfileDao;
import domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProfileService implements business.interfaces.ProfileService {

    ProfileDao profileDao;

    @Inject
    public void setProfileDao(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    @Override
    public List<Profile> getProfileList(){
        return profileDao.getAllProfiles();
    }

    @Override
    public Profile getProfileByName(String name) {
        return profileDao.getProfileByName(name);
    }

    @Override
    public Profile getProfileById(long id) {
        return profileDao.find(id);
    }

    @Override
    public List<Profile> getSubscribersOfSubreddit(String subreddit) {
        return null;
    }

    @Override
    public List<Profile> getModeratorsOfSubreddit(String subreddit) {
        return null;
    }
}

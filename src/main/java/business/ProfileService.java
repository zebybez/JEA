package business;

import data.ProfileDaoJPA;
import domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProfileService implements business.interfaces.ProfileService {

    ProfileDaoJPA profileDaoJPA;

    @Inject
    public void setProfileDaoJPA(ProfileDaoJPA profileDaoJPA){
        this.profileDaoJPA = profileDaoJPA;
    }

    @Override
    public List<Profile> getProfileList(){
        return profileDaoJPA.getAllProfiles();
    }

    @Override
    public Profile getProfileByName(String name) {
        return profileDaoJPA.getProfileByName(name);
    }

    @Override
    public Profile getProfileById(long creatorId) {
        return profileDaoJPA.find(creatorId);
    }
}

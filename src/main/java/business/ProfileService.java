package business;

import data.ProfileDaoJPA;
import domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProfileService {

    ProfileDaoJPA profileDaoJPA;

    @Inject
    public void setProfileDaoJPA(ProfileDaoJPA profileDaoJPA){
        this.profileDaoJPA = profileDaoJPA;
    }

    public List<Profile> getProfileList(){
        return profileDaoJPA.getAllProfiles();
    }

}

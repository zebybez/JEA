package business;

import data.ProfileDao;
import domain.Account;
import domain.Profile;
import util.security.HashUtil;
import util.security.HashUtilMD5;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProfileService {

    ProfileDao profileDao;

    @Inject
    public void setProfileDao(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    public List<Profile> getProfileList(){
        return profileDao.getAllProfiles();
    }

}

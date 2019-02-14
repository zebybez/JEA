package business;

import domain.Account;
import domain.Profile;
import util.security.HashUtil;
import util.security.HashUtilMD5;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProfileService {


    public void registerNewAccount(String email, String password){
        Profile profile = new Profile();


    }

}

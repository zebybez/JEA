package business;

import domain.Account;
import domain.Profile;

import javax.ejb.Stateless;

@Stateless
public class ProfileService {
    public void registerNewAccount(String email, String password){
        Profile profile = new Profile();
        Account account = new Account();

    }

}

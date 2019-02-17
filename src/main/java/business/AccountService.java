package business;

import data.ProfileDao;
import data.interfaces.AccountDao;
import domain.Account;
import domain.Profile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.security.HashUtil;
import util.security.Payload;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountService {
    private HashUtil hashUtil;
    private AccountDao accountDao;
    private ProfileDao profileDao;


    public AccountService() {
    }

    @Inject
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Inject
    public void setProfileDao(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    @Inject
    public void setHashUtil(HashUtil hashUtil) {
        this.hashUtil = hashUtil;
    }

    public Payload getPayloadByCredentials(String email, String password) {
        //  Payload payload = new Payload();
        throw new NotImplementedException();
    }

    public Account addNewAccount(String email, String name, String password) {
        Account account = new Account();

        account.setEmail(email);
        String salt = hashUtil.generateSalt();
        account.setSalt(salt);
        account.setPasswordHash(hashUtil.hashString(salt, password));

        Profile profile = new Profile();

        profile.setName(name);
        account.setProfile(profile);
        profileDao.saveProfile(profile);
        return accountDao.saveAccount(account);
    }
}

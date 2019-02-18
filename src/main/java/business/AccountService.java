package business;

import data.ProfileDao;
import data.interfaces.AccountDao;
import domain.Account;
import domain.Profile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.security.HashUtil;
import util.security.JWTHelper;
import util.security.Payload;
import util.security.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

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
        account.setRole(Role.Regular);
        String salt = hashUtil.generateSalt();
        account.setSalt(salt);
        account.setPasswordHash(hashUtil.hashString(salt, password));

        Profile profile = new Profile();

        profile.setName(name);
        account.setProfile(profile);
        return accountDao.saveAccount(account);
    }

    public String login(String email, String password) throws SecurityException{
        //check credentials
        Account account = accountDao.getAccountByEmail(email);
        if(hashUtil.hashString(account.getSalt(), password).equals(account.getPasswordHash())){
            Payload payload = new Payload(email, account.getProfile().getUuid(), account.getRole());
            return JWTHelper.getInstance().generatePrivateKey(payload);
        }
        throw new SecurityException("wrong credentials");
    }

    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }
}

package business;

import business.interfaces.ProfileService;
import data.interfaces.AccountDao;
import domain.Account;
import domain.Profile;
import util.annotations.Secured;
import util.security.HashUtil;
import util.security.JWTHelper;
import util.security.Payload;
import util.security.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AccountService implements business.interfaces.AccountService {
    private HashUtil hashUtil;
    private AccountDao accountDao;
    private ProfileService profileService;

    public AccountService() {
    }

    @Inject
    public void setProfileService(ProfileService profileService){
        this.profileService = profileService;
    }

    @Inject
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Inject
    public void setHashUtil(HashUtil hashUtil) {
        this.hashUtil = hashUtil;
    }


    @Override
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
        return accountDao.persist(account);
    }

    @Override
    public String login(String email, String password) throws SecurityException{
        //check credentials
        Account account = accountDao.getAccountByEmail(email);
        if(hashUtil.hashString(account.getSalt(), password).equals(account.getPasswordHash())){
            Payload payload = new Payload(email, account.getProfile().getName(), account.getProfile().getUuid(), account.getRole());
            return JWTHelper.getInstance().generatePrivateKey(payload);
        }
        throw new SecurityException("wrong credentials");
    }

    @Secured
    @Override
    public List<Account> getAllAccounts() {
        return accountDao.getAll();
    }

    @Override
    public Account getAccountByProfileName(String name){
        Profile profile = profileService.getProfileByName(name);
        return accountDao.getByProfileId(profile.getId());
    }

    @Override
    public Account setAdmin(long accountId){
        Account account = accountDao.find(accountId);
        account.setRole(Role.Admin);
        return accountDao.merge(account);
    }

    @Override
    public Account setRegular(long accountId){
        Account account = accountDao.find(accountId);
        account.setRole(Role.Regular);
        return accountDao.merge(account);
    }

    @Override
    public void removeAccount(Account account) {
        accountDao.delete(account);
    }
}

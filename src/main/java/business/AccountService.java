package business;

import data.interfaces.AccountDao;
import domain.Account;
import domain.Profile;
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

    public AccountService() {
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
            Payload payload = new Payload(email, account.getProfile().getUuid(), account.getRole());
            return JWTHelper.getInstance().generatePrivateKey(payload);
        }
        throw new SecurityException("wrong credentials");
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.getAll();
    }
}

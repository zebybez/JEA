package business;

import data.interfaces.AccountDao;
import domain.Account;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.security.HashUtil;
import util.security.HashUtilMD5;
import util.security.Payload;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountService {
    HashUtil hashUtil;
    AccountDao accountDao;

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

    public Payload getPayloadByCredentials(String email, String password) {
        //  Payload payload = new Payload();
        throw new NotImplementedException();
    }

    public Account addNewAccount(String email, String password) {
        Account account = new Account();

        account.setEmail(email);
        String salt = hashUtil.generateSalt();
        account.setSalt(salt);
        account.setPasswordHash(hashUtil.hashString(salt, password));

        return accountDao.addAccount(account);
    }
}

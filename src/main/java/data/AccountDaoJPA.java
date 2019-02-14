package data;

import data.interfaces.AccountDao;
import domain.Account;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AccountDaoJPA implements AccountDao {

    @Override
    public Account saveAccount(Account account) {
        throw new NotImplementedException();
    }
}

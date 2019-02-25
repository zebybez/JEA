package data.interfaces;

import domain.Account;

import java.util.List;

public interface AccountDao extends BaseDao<Account> {
    Account getAccountByEmail(String email);
    List<Account> getAll();
}

package data.interfaces;

import domain.Account;

import java.util.List;

public interface AccountDao  {
    public Account saveAccount(Account account);

    Account getAccountByEmail(String email);

    List<Account> getAllAccounts();
}

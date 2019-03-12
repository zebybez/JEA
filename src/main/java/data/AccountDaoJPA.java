package data;

import data.interfaces.AccountDao;
import domain.Account;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AccountDaoJPA extends BaseDaoJPA<Account> implements AccountDao {

    public AccountDaoJPA() {
        super(Account.class);
    }

    @Override
    public Account getAccountByEmail(String email){
        return getEm().createNamedQuery("account.findByEmail", Account.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<Account> getAll() {
        return getEm().createNamedQuery("account.getAll", Account.class)
                .getResultList();
    }

    @Override
    public Account getByProfileId(long id){
        return getEm().createQuery("SELECT a FROM Account a WHERE a.profile.id = :id", Account.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}

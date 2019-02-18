package data;

import data.interfaces.AccountDao;
import domain.Account;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AccountDaoJPA implements AccountDao {

    EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.em = em;
    }

    @Override
    public Account saveAccount(Account account) {
        em.persist(account);
        return account;
    }

    public Account getAccountByEmail(String email){
        return em.createNamedQuery("account.findByEmail", Account.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<Account> getAllAccounts(){
         return em.createNamedQuery("account.getAll", Account.class)
                 .getResultList();
    }

}

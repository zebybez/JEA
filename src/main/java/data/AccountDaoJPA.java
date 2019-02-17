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
        em.flush();
        return account;
    }

    public Account getAccountByEmail(String email){
        TypedQuery<Account> query = em.createNamedQuery("account.findByEmail", Account.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

}

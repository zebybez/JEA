package data;

import data.interfaces.AccountDao;
import domain.Account;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AccountDaoJPA implements AccountDao {

    EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.em = em;
    }

    @Override
    public Account addAccount(Account account) {
        em.persist(account);
        return account;
    }

}

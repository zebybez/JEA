package data;

import domain.Profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ProfileDao {
    EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em){
        this.em = em;
    }

    public Profile saveProfile(Profile profile){
        em.persist(profile);
        return profile;
    }


    public List<Profile> getAllProfiles() {
        TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p", Profile.class);
        return query.getResultList();
    }
}

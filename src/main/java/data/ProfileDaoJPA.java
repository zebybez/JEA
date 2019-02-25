package data;

import domain.Profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ProfileDaoJPA extends BaseDaoJPA<Profile> {

    public ProfileDaoJPA() {
        super(Profile.class);
    }

    public List<Profile> getAllProfiles() {
        return getEm().createQuery("SELECT p FROM Profile p", Profile.class)
                .getResultList();
    }

    public Profile getProfileByName(String name) {
        return getEm().createQuery("SELECT p FROM Profile p WHERE p.name = :name", Profile.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}

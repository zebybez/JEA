package data;

import data.interfaces.ProfileDao;
import domain.Profile;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Default
public class ProfileDaoJPA extends BaseDaoJPA<Profile> implements ProfileDao {

    public ProfileDaoJPA() {
        super(Profile.class);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return getEm().createQuery("SELECT p FROM Profile p", Profile.class)
                .getResultList();
    }

    @Override
    public Profile getProfileByName(String name) {
        return getEm().createQuery("SELECT p FROM Profile p WHERE p.name = :name", Profile.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}

package mariia.maltseva.dao;

import mariia.maltseva.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User newUser) {
        em.persist(newUser);
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT u FROM User u WHERE u.email = :email";
        List<User> users = em.createQuery(query, User.class)
                .setParameter("email", email)
                .getResultList();
        return (users.size() > 0) ? users.get(0) : null;
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }


}

package caveatemptor.features.users.repositories;

import caveatemptor.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
    @PersistenceContext
    private EntityManager em;
    @Override
    public void persist(User user) {
        em.persist(user);
    }



    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> query = em.createQuery("SELECT user FROM User AS user WHERE user.userName = :userName", User.class);
        query.setParameter("userName", userName);
        return query.getSingleResult();
    }
}

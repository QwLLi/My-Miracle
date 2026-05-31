package myApp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import myApp.model.User;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class DaoImpl implements Dao{

     @PersistenceContext
     private EntityManager entityManager;

    @Override
    public void saveUser(String firstName, String lastName, int year) {
        User user = new User(firstName , lastName , year);
        entityManager.persist(user);

    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class,id));

    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}

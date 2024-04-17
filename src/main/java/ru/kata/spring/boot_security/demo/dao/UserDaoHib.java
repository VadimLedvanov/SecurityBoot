package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoHib implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void addUser(User user) {
        em.persist(user);
    }


    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        User user = getUser(id);
        em.remove(user);
    }

    @Override
    public void changeUser(Long id, User user) {
        User updateUser = getUser(id);
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        em.merge(updateUser);
    }
    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User").getResultList();
    }
}

package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User getById(int id) {
        return (User) sessionFactory.getCurrentSession().load(User.class, id);
    }

    @Override
    public User getUser(String username, String password) {
        String hql = "from User where username=:username and password=:password";
        return (User) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    public User getByUsername(String username) {
        String hql = "from User where username=:username";
        return (User) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("username", username)
                .uniqueResult();
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void updateResults(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User getByEmail(String email) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where email=:email")
                .setParameter("email", email)
                .uniqueResult();
    }

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .list();
    }

    @Override
    public void deleteUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        if (user != null) {
            session.delete(user);
        }
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}

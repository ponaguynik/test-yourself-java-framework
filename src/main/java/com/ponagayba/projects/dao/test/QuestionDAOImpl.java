package com.ponagayba.projects.dao.test;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.Question;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAOImpl extends AbstractDAO implements QuestionDAO {

    @Override
    public List<Question> getAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Question.class)
                .list();
    }

    @Override
    public void create(Question question) {
        sessionFactory.getCurrentSession().persist(question);
    }

    @Override
    public void delete(Question question) {
        sessionFactory.getCurrentSession().delete(question);
    }

    @Override
    public Question getById(int id) {
        return (Question) sessionFactory.getCurrentSession().get(Question.class, id);
    }

    @Override
    public void update(Question question) {
        sessionFactory.getCurrentSession().update(question);
    }
}

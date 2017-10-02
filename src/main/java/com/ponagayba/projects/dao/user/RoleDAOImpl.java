package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDAOImpl extends AbstractDAO implements RoleDAO {

    @Override
    public Role getById(int roleId) {
        return (Role) sessionFactory.getCurrentSession().get(Role.class, roleId);
    }

    @Override
    public Role getByName(String name) {
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("from Role where name=:name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public List<Role> getAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Role.class)
                .list();
    }
}

package com.ponagayba.projects.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public abstract class AbstractDAO {

    @Autowired
    protected SessionFactory sessionFactory;

}

package com.ponagayba.projects.dao;


import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO implements AutoCloseable {

    protected Connection connection;

    @Autowired
    protected DataSource dataSource;

    public AbstractDAO() {
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

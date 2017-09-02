package com.ponagayba.projects.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO implements AutoCloseable {

    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

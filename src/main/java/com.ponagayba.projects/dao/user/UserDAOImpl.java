package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    public UserDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User findById(int id) throws SQLException {
        String query =
                "SELECT username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    id,
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("token"),
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
        }
        return user;
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        String query =
                "SELECT id, email, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE username=? AND password=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    username,
                    resultSet.getString("email"),
                    password,
                    resultSet.getString("token"),
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
        }
        return user;
    }

    @Override
    public boolean userExists(String username) throws SQLException {
        String query =
                "SELECT id FROM test_yourself.user " +
                "WHERE username=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void create(User user) throws SQLException {
        String query =
                "INSERT INTO test_yourself.user(username, email, password) " +
                "VALUES(?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.execute();
    }

    @Override
    public void updateToken(int userId, String token) throws SQLException {
        String query =
                "UPDATE test_yourself.user " +
                "SET token=? " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        preparedStatement.setInt(2, userId);
        preparedStatement.execute();
    }

    @Override
    public User findByToken(String token) throws SQLException {
        String query =
                "SELECT id, username, email, password, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE token=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    token,
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
        }
        return user;
    }

    @Override
    public void removeToken(String token) throws SQLException {
        String query =
                "UPDATE test_yourself.user " +
                "SET token=NULL " +
                "WHERE token=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        preparedStatement.execute();
    }

    @Override
    public void updateResults(User user) throws SQLException {
        String query =
                "UPDATE test_yourself.user " +
                "SET last_result=?, best_result=? " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, user.getLastResult());
        preparedStatement.setInt(2, user.getBestResult());
        preparedStatement.setInt(3, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String query =
                "SELECT id, username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE email=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    email,
                    resultSet.getString("password"),
                    resultSet.getString("token"),
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query =
                "SELECT id, username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("token"),
                    resultSet.getInt("last_result"),
                    resultSet.getInt("best_result")
            );
            result.add(user);
        }
        return result;
    }

    @Override
    public void deleteUser(int userId) throws SQLException {
        String query =
                "DELETE FROM test_yourself.user " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.execute();
    }

    @Override
    public void update(User user) throws SQLException {
        String query =
                "UPDATE test_yourself.user " +
                "SET username=?, email=?, password=?, token=?, last_result=?, best_result=? " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getToken());
        preparedStatement.setInt(5, user.getLastResult());
        preparedStatement.setInt(6, user.getBestResult());
        preparedStatement.setInt(7, user.getId());
        preparedStatement.executeUpdate();
    }
}

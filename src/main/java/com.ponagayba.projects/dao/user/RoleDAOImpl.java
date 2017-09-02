package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl extends AbstractDAO implements RoleDAO {

    public RoleDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role findById(int roleId) throws SQLException {
        String query =
                "SELECT name " +
                "FROM test_yourself.role " +
                "WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roleId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Role(
                    roleId,
                    resultSet.getString("name")
            );
        }
        return null;
    }

    @Override
    public Role findByName(String name) throws SQLException {
        String query =
                "SELECT id, name " +
                "FROM test_yourself.role " +
                "WHERE name=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        }
        return null;
    }

    @Override
    public List<Role> getUserRoles(int userId) throws SQLException {
        String query =
                "SELECT r.id, r.name " +
                "FROM test_yourself.user u " +
                "JOIN test_yourself.user_to_role ur ON ur.user_id=u.id " +
                "JOIN test_yourself.role r ON r.id=ur.role_id " +
                "WHERE u.id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Role> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            ));
        }
        return result;
    }

    @Override
    public List<Role> getAll() throws SQLException {
        String query =
                "SELECT id, name " +
                "FROM test_yourself.role;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Role> result = new ArrayList<>();
        while (resultSet.next()) {
            Role role = new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
            result.add(role);
        }
        return result;
    }

    @Override
    public void deleteUserRoles(int userId) throws SQLException {
        String query =
                "DELETE FROM test_yourself.user_to_role " +
                "WHERE user_id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.execute();
    }

    @Override
    public void addRoleToUser(int userId, Role role) throws SQLException {
        String query =
                "INSERT INTO test_yourself.user_to_role(user_id, role_id) " +
                "VALUES(?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, role.getId());
        preparedStatement.execute();
    }


}

package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDAOImpl extends AbstractDAO implements RoleDAO {

    @Override
    public Role findById(int roleId) {
        String query =
                "SELECT id, name " +
                "FROM test_yourself.role " +
                "WHERE id=?;";
        return jdbcTemplate.queryForObject(query, new Object[] {roleId}, new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public Role findByName(String name) {
        String query =
                "SELECT id, name " +
                "FROM test_yourself.role " +
                "WHERE name=?;";
        return jdbcTemplate.queryForObject(query, new Object[] {name}, new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public List<Role> getUserRoles(int userId) {
        String query =
                "SELECT r.id, r.name " +
                "FROM test_yourself.user u " +
                "JOIN test_yourself.user_to_role ur ON ur.user_id=u.id " +
                "JOIN test_yourself.role r ON r.id=ur.role_id " +
                "WHERE u.id=?;";
        return jdbcTemplate.query(query, new Object[] {userId}, new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public List<Role> getAll() {
        String query =
                "SELECT id, name " +
                "FROM test_yourself.role;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public void deleteUserRoles(int userId) {
        String query =
                "DELETE FROM test_yourself.user_to_role " +
                "WHERE user_id=?;";
        jdbcTemplate.update(query, userId);
    }

    @Override
    public void addRoleToUser(int userId, Role role) {
        String query =
                "INSERT INTO test_yourself.user_to_role(user_id, role_id) " +
                "VALUES(?, ?);";
        jdbcTemplate.update(query, userId, role.getId());
    }

}

package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.dao.AbstractDAO;
import com.ponagayba.projects.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public User findById(int id) {
        String query =
                "SELECT id, username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE id=?;";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUser(String username, String password) {
        String query =
                "SELECT id, username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE username=? AND password=?;";

        return jdbcTemplate.queryForObject(query, new Object[]{username, password}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getByUsername(String username) {
        String query =
                "SELECT id, username, password, email, last_result, best_result, enabled FROM test_yourself.user " +
                "WHERE username=?;";
        return jdbcTemplate.queryForObject(query, new Object[] {username}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void create(User user) {
        String query =
                "INSERT INTO test_yourself.user(username, email, password) " +
                "VALUES(?, ?, ?);";
        jdbcTemplate.update(query, user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Override
    public void updateToken(int userId, String token) {
        String query =
                "UPDATE test_yourself.user " +
                "SET token=? " +
                "WHERE id=?;";
        jdbcTemplate.update(query, token, userId);
    }

    @Override
    public User findByToken(String token) {
        String query =
                "SELECT id, username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE token=?;";

        return jdbcTemplate.queryForObject(query, new Object[]{token}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void removeToken(String token) {
        String query =
                "UPDATE test_yourself.user " +
                "SET token=NULL " +
                "WHERE token=?;";
        jdbcTemplate.update(query, token);
    }

    @Override
    public void updateResults(User user) {
        String query =
                "UPDATE test_yourself.user " +
                "SET last_result=?, best_result=? " +
                "WHERE id=?;";
        jdbcTemplate.update(query, user.getLastResult(), user.getBestResult(), user.getId());
    }

    @Override
    public User findByEmail(String email) {
        String query =
                "SELECT id, username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user " +
                "WHERE email=?;";
        return jdbcTemplate.queryForObject(query, new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getAll() {
        String query =
                "SELECT id, username, email, password, token, last_result, best_result " +
                "FROM test_yourself.user;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void deleteUser(int userId) {
        String query =
                "DELETE FROM test_yourself.user " +
                "WHERE id=?;";
        jdbcTemplate.update(query, userId);
    }

    @Override
    public void update(User user) {
        String query =
                "UPDATE test_yourself.user " +
                "SET username=?, email=?, password=?, token=?, last_result=?, best_result=? " +
                "WHERE id=?;";
        jdbcTemplate.update(query, user.getUsername(), user.getEmail(), user.getPassword(), user.getToken(),
                user.getLastResult(), user.getBestResult(), user.getId());
    }
}

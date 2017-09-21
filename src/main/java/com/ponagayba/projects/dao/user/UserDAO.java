package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User getUser(String username, String password);

    User findById(int id);

    User getByUsername(String username);

    void create(User user);

    void updateToken(int userId, String token);

    User findByToken(String token);

    void removeToken(String token);

    void updateResults(User user);

    User findByEmail(String email);

    List<User> getAll();

    void deleteUser(int userId);

    void update(User user);
}

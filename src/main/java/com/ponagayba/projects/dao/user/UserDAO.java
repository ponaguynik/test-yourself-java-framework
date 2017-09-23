package com.ponagayba.projects.dao.user;

import com.ponagayba.projects.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User getUser(String username, String password);

    User getById(int id);

    User getByUsername(String username);

    void create(User user);

    void updateResults(User user);

    User getByEmail(String email);

    List<User> getAll();

    void delete(User user);

    void update(User user);
}

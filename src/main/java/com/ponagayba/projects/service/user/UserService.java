package com.ponagayba.projects.service.user;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.List;

public interface UserService {

    User findById(int id);

    User getUser(String username, String password);

    boolean usernameExists(String username);

    void addNewUser(User user);

    void updateToken(int userId, String token);

    User findByToken(String token);

    void removeToken(String token);

    void updateResults(User user, TestResult testResult);

    User getUserFromCookies(Cookie[] cookies);

    boolean isEmailFree(String email);

    List<User> getAll();

    void deleteUser(int userId);

    void updateUser(User user);

    boolean emailExists(String email);

    User getByUsername(String username);
}

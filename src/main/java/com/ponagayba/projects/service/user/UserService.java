package com.ponagayba.projects.service.user;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.List;

public interface UserService {

    User findById(int id) throws SQLException;

    User getUser(String username, String password) throws SQLException;

    boolean usernameExists(String username) throws SQLException;

    void addNewUser(User user) throws SQLException;

    void updateToken(int userId, String token) throws SQLException;

    User findByToken(String token) throws SQLException;

    void removeToken(String token) throws SQLException;

    void updateResults(User user, TestResult testResult) throws SQLException;

    User getUserFromCookies(Cookie[] cookies) throws SQLException;

    boolean isEmailFree(String email) throws SQLException;

    List<User> getAll() throws SQLException;

    void deleteUser(int userId) throws SQLException;

    void updateUser(User user) throws SQLException;

    boolean emailExists(String email) throws SQLException;
}

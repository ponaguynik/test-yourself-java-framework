package com.ponagayba.projects.service.user;

import com.ponagayba.projects.dao.user.RoleDAO;
import com.ponagayba.projects.dao.user.UserDAO;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public User findById(int id) throws SQLException {
        User result = userDAO.findById(id);
        if (result != null) {
            result.setRoles(roleDAO.getUserRoles(result.getId()));
        }
        return result;
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        User result = userDAO.getUser(username, password);
        if (result != null) {
            result.setRoles(roleDAO.getUserRoles(result.getId()));
        }
        return result;
    }

    @Override
    public boolean userExists(String username) throws SQLException {
        return userDAO.userExists(username);
    }

    @Override
    public void addNewUser(User user) throws SQLException {
        userDAO.create(user);
        User userDB = userDAO.getUser(user.getUsername(), user.getPassword());
        for (Role role : user.getRoles()) {
            roleDAO.addRoleToUser(userDB.getId(), role);
        }
    }

    @Override
    public void updateToken(int userId, String token) throws SQLException {
        userDAO.updateToken(userId, token);
    }

    @Override
    public User findByToken(String token) throws SQLException {
        User result = userDAO.findByToken(token);
        if (result != null) {
            result.setRoles(roleDAO.getUserRoles(result.getId()));
        }
        return result;
    }

    @Override
    public void removeToken(String token) throws SQLException {
        userDAO.removeToken(token);
    }

    @Override
    public void updateResults(User user, TestResult testResult) throws SQLException {
        int lastResult = Factory.getTestService().percentageOfCorrectAnswers(testResult.getQuestions());
        user.setLastResult(lastResult);
        if (user.getLastResult() > user.getBestResult()) {
            user.setBestResult(user.getLastResult());
        }
        userDAO.updateResults(user);
    }

    @Override
    public User getUserFromCookies(Cookie[] cookies) throws SQLException {
        User result = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("token")) {
                    result = findByToken(cookie.getValue());
                }
            }
        }
        if (result != null) {
            result.setRoles(roleDAO.getUserRoles(result.getId()));
        }
        return result;
    }

    @Override
    public boolean isEmailFree(String email) throws SQLException {
        return userDAO.findByEmail(email) == null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> result = userDAO.getAll();
        for (User user : result) {
            user.setRoles(roleDAO.getUserRoles(user.getId()));
        }
        return result;
    }

    @Override
    public void deleteUser(int userId) throws SQLException {
        Factory.getTestResultService().deleteUserTestResults(userId);
        roleDAO.deleteUserRoles(userId);
        userDAO.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        userDAO.update(user);
    }
}

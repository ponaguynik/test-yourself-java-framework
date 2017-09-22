package com.ponagayba.projects.service.user;

import com.ponagayba.projects.dao.user.RoleDAO;
import com.ponagayba.projects.dao.user.UserDAO;
import com.ponagayba.projects.exception.EmailExistsException;
import com.ponagayba.projects.exception.UsernameExistsException;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;
import com.ponagayba.projects.service.test.TestResultService;
import com.ponagayba.projects.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private TestService testService;

    @Autowired
    private TestResultService testResultService;

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public User getUser(String username, String password) {
        return userDAO.getUser(username, password);
    }

    @Override
    public boolean usernameExists(String username) {
        return userDAO.getByUsername(username) != null;
    }

    @Override
    public void addNewUser(User user) throws UsernameExistsException, EmailExistsException {
        if (usernameExists(user.getUsername())) {
            throw new UsernameExistsException();
        }
        if (emailExists(user.getEmail())) {
            throw new EmailExistsException();
        }
        addDefaultRole(user);
        user.setEnabled(true);
        userDAO.save(user);
    }

    @Override
    public void updateResults(User user, TestResult testResult) {
        int lastResult = testService.percentageOfCorrectAnswers(testResult.getQuestions());
        user.setLastResult(lastResult);
        if (user.getLastResult() > user.getBestResult()) {
            user.setBestResult(user.getLastResult());
        }
        userDAO.updateResults(user);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userDAO.getAll();
        for (User user : result) {
            user.setRoles(roleDAO.getUserRoles(user.getId()));
        }
        return result;
    }

    @Override
    public void deleteUser(int userId) {
        testResultService.deleteUserTestResults(userId);
        roleDAO.deleteUserRoles(userId);
        userDAO.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    public boolean emailExists(String email) {
        return userDAO.getByEmail(email) != null;
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    private void addDefaultRole(User user) {
        Role roleUser = roleDAO.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);
        user.setRoles(roles);
    }
}

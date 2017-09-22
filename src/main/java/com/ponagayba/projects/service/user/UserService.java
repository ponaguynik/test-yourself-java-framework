package com.ponagayba.projects.service.user;

import com.ponagayba.projects.exception.EmailExistsException;
import com.ponagayba.projects.exception.UsernameExistsException;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;

import java.util.List;

public interface UserService {

    User getById(int id);

    User getUser(String username, String password);

    boolean usernameExists(String username);

    void addNewUser(User user) throws UsernameExistsException, EmailExistsException;

    void updateResults(User user, TestResult testResult);

    List<User> getAll();

    void deleteUser(int userId);

    void updateUser(User user);

    boolean emailExists(String email);

    User getByUsername(String username);

}

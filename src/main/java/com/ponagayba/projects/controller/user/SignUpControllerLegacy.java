package com.ponagayba.projects.controller.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.RoleService;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SignUpControllerLegacy implements Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

/*    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");

        String validationResult = validate(username, email, password, confPassword);
        if (validationResult == null) {
            result.setRedirect(true);
            addNewUser(username, email, password);
            result.setView("/login");
            result.setAttribute("message", "User has been successfully created!");
        } else {
            result.setView("user/signUp");
            result.setAttribute("error", validationResult);
        }
        return result;
    }

    private String validate(String username, String email, String password, String confPassword) throws SQLException {
        String result = null;
        if (userService.usernameExists(username)) {
            result = "Username with such username already exists.";
        } else if (!userService.isEmailFree(email)) {
            result = "This email is already registered.";
        } else if (!password.equals(confPassword)) {
            result = "Password does not match the confirm password.";
        }
        return result;
    }

    private void addNewUser(String username, String email, String password) throws SQLException {
        User newUser = new User(username, email, password);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("user"));
        newUser.setRoles(roles);
        userService.addNewUser(newUser);
    }*/
}

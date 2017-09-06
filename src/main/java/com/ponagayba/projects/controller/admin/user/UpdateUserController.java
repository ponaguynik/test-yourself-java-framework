package com.ponagayba.projects.controller.admin.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.RoleService;
import com.ponagayba.projects.service.user.UserService;
import com.ponagayba.projects.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateUserController implements Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        User user = userService.findById(userId);

        if (!user.getUsername().equals(username) && userService.usernameExists(username)) {
            result.setAttribute("error", "User with such username already exists.");
        } else if (!user.getEmail().equals(email) && !userService.isEmailFree(email)) {
            result.setAttribute("error", "Email is already registered.");
        } else {
            user.setUsername(username);
            user.setEmail(email);
            userService.updateUser(user);
        }
        List<Role> roles = roleService.getAll();

        result.setAttribute("roles", roles);
        result.setAttribute("managedUser", user);
        result.setAttribute("page", "manageUser");
        return result;
    }
}

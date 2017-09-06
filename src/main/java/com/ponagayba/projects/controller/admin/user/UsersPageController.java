package com.ponagayba.projects.controller.admin.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.UserService;
import com.ponagayba.projects.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UsersPageController implements Controller {

    @Autowired
    private UserService userService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        List<User> users = userService.getAll();
        Collections.reverse(users);
        result.setAttribute("users", users);
        result.setAttribute("page", "users");
        return result;
    }
}

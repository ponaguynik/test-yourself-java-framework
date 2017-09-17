package com.ponagayba.projects.controller.admin.user;

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
import java.util.List;

public class ManageUserPageController implements Controller {

/*    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        int userId = Integer.parseInt(request.getParameter("userId"));
        User managedUser = userService.findById(userId);
        List<Role> roles = roleService.getAll();
        result.setAttribute("managedUser", managedUser);
        result.setAttribute("roles", roles);
        result.setAttribute("page", "manageUser");
        return result;
    }*/
}

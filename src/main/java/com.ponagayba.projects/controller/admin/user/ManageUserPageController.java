package com.ponagayba.projects.controller.admin.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageUserPageController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        int userId = Integer.parseInt(request.getParameter("userId"));
        User managedUser = Factory.getUserService().findById(userId);
        List<Role> roles = Factory.getRoleService().getAll();
        result.setAttribute("managedUser", managedUser);
        result.setAttribute("roles", roles);
        result.setAttribute("page", "manageUser");
        return result;
    }
}

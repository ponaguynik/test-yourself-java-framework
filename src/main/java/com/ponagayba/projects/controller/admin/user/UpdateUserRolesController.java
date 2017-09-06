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
import java.util.ArrayList;
import java.util.List;

public class UpdateUserRolesController implements Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.findById(userId);
        int[] rolesId = toIntArray(request.getParameterValues("role"));
        user.setRoles(getRolesById(rolesId, roleService));
        roleService.updateUserRoles(user);

        result.setAttribute("roles", roleService.getAll());
        result.setAttribute("managedUser", user);
        result.setAttribute("page", "manageUser");
        return result;
    }

    private int[] toIntArray(String[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }

    private List<Role> getRolesById(int[] rolesId, RoleService roleService) throws SQLException {
        List<Role> result = new ArrayList<>();
        for (int roleId : rolesId) {
            result.add(roleService.findById(roleId));
        }
        return result;
    }
}

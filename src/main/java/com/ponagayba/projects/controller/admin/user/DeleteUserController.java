package com.ponagayba.projects.controller.admin.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.service.user.UserService;
import com.ponagayba.projects.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserController implements Controller {

    @Autowired
    private UserService userService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("/admin/users");
        int userId = Integer.parseInt(request.getParameter("userId"));
        userService.deleteUser(userId);
        result.setRedirect(true);
        return result;
    }
}

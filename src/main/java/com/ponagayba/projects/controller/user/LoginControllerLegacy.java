package com.ponagayba.projects.controller.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class LoginControllerLegacy implements Controller {

    private static final String INCORRECT = "The username or password is incorrect";

    @Autowired
    private UserService userService;

/*    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        if ((user = userService.getUser(username, password)) != null) {
            result.setRedirect(true);
            result.setView("home");
            registerToken(user);
            result.addCookie(new Cookie("TOKEN", user.getToken()));
        } else {
            result.setView("user/login");
            result.setAttribute("error", INCORRECT);
        }
        return result;
    }

    private void registerToken(User user) throws SQLException {
        String token = user.getUsername() + System.nanoTime();
        user.setToken(token);
        userService.updateToken(user.getId(), user.getToken());
    }*/
}

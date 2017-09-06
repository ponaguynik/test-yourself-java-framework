package com.ponagayba.projects.controller.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.service.user.UserService;
import com.ponagayba.projects.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutController implements Controller {

    @Autowired
    private UserService userService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("home");
        result.setRedirect(true);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("TOKEN")) {
                    userService.removeToken(cookie.getValue());
                    cookie.setMaxAge(0);
                    result.addCookie(cookie);
                    break;
                }
            }
        }
        return result;
    }
}

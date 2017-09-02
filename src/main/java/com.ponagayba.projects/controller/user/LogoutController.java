package com.ponagayba.projects.controller.user;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("home");
        result.setRedirect(true);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("TOKEN")) {
                    Factory.getUserService().removeToken(cookie.getValue());
                    cookie.setMaxAge(0);
                    result.addCookie(cookie);
                    break;
                }
            }
        }
        return result;
    }
}

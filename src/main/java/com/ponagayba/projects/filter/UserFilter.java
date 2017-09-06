package com.ponagayba.projects.filter;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class UserFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user;
        try {
            user = userService.getUserFromCookies(req.getCookies());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        if (user != null) {
            req.setAttribute("user", user);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

package com.ponagayba.projects.filter;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class AuthenticationFilter implements Filter {

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
        user = userService.getUserFromCookies(req.getCookies());
        if (user == null) {
            req.setAttribute("error", "Please login first.");
            req.setAttribute("uri", "/login");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

package com.ponagayba.projects.filter;

import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class AuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user;
        try {
            user = Factory.getUserService().getUserFromCookies(req.getCookies());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
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

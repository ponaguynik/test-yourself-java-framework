package com.ponagayba.projects.filter;

import com.ponagayba.projects.model.Role;
import com.ponagayba.projects.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        User user = (User) request.getAttribute("user");
        if (user == null || !user.getRoles().contains(new Role("admin"))) {
            resp.sendError(404);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

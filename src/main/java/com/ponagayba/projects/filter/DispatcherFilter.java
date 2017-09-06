package com.ponagayba.projects.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DispatcherFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestURI().startsWith("/resources") || request.getRequestURI().contains(".")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (request.getAttribute("uri") != null) {
                request.getRequestDispatcher("/pages" + request.getAttribute("uri")).forward(servletRequest, servletResponse);
            } else {
                request.getRequestDispatcher("/pages" + request.getRequestURI()).forward(request, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}

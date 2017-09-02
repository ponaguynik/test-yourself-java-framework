package com.ponagayba.projects.servlet;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelAndView {

    private String view;
    private Map<String, Object> attributes = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private List<Cookie> cookies = new ArrayList<>();
    private boolean redirect;

    public ModelAndView() {
    }

    public ModelAndView(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}

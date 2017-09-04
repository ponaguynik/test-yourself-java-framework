package com.ponagayba.projects.servlet;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.exception.PageNotFoundException;
import com.ponagayba.projects.factory.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServletLegacy extends HttpServlet {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".jsp";

    private Map<String, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put("GET/pages/", Factory.getHomePageController());
        controllerMap.put("GET/pages/home", Factory.getHomePageController());
        controllerMap.put("GET/pages/signup", Factory.getSignUpPageController());
        controllerMap.put("POST/pages/signup", Factory.getSignUpController());
        controllerMap.put("GET/pages/login", Factory.getLoginPageController());
        controllerMap.put("POST/pages/login", Factory.getLoginController());
        controllerMap.put("POST/pages/logout", Factory.getLogoutController());
        controllerMap.put("GET/pages/test/start", Factory.getTestPageController());
        controllerMap.put("GET/pages/test", Factory.getTestController());
        controllerMap.put("POST/pages/test", Factory.getTestController());
        controllerMap.put("POST/pages/test/finish", Factory.getFinishTestController());
        controllerMap.put("POST/pages/test/answer", Factory.getAnswerTestController());
        controllerMap.put("POST/pages/test/cancel", Factory.getCancelAnswerTestController());
        controllerMap.put("GET/pages/results", Factory.getResultsPageController());
        controllerMap.put("GET/pages/admin", Factory.getAdminUsersController());
        controllerMap.put("GET/pages/admin/users", Factory.getAdminUsersController());
        controllerMap.put("GET/pages/admin/users/manage-user", Factory.getManageUserPageController());
        controllerMap.put("POST/pages/admin/users/delete-user", Factory.getDeleteUserController());
        controllerMap.put("POST/pages/admin/users/manage-user/update", Factory.getUpdateUserController());
        controllerMap.put("POST/pages/admin/users/manage-user/update-roles", Factory.getUpdateUserRolesController());
        controllerMap.put("GET/pages/admin/questions", Factory.getQuestionsPageController());
        controllerMap.put("GET/pages/admin/questions/add", Factory.getAddQuestionPageController());
        controllerMap.put("POST/pages/admin/questions/add", Factory.getAddQuestionController());
        controllerMap.put("POST/pages/admin/questions/delete", Factory.getDeleteQuestionController());
        controllerMap.put("GET/pages/admin/questions/edit", Factory.getEditQuestionPageController());
        controllerMap.put("POST/pages/admin/questions/edit", Factory.getEditQuestionController());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Controller controller = getController(request);
            ModelAndView modelAndView = controller.process(request);
            render(modelAndView, request, response);
        } catch (PageNotFoundException e) {
            response.sendError(404);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    private Controller getController(HttpServletRequest request) {
        String methodUri = request.getMethod() + request.getRequestURI().split(";")[0];
        Controller result = controllerMap.get(methodUri);
        if (result == null) {
            throw new PageNotFoundException();
        }
        return result;
    }

    private void render(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addCookies(modelAndView, response);
        addSessionAttributes(modelAndView, request);
        if (modelAndView.isRedirect()) {
            response.sendRedirect(modelAndView.getView());
        } else {
            addAttributes(modelAndView, request);
            request.getRequestDispatcher(PREFIX + modelAndView.getView() + SUFFIX).forward(request, response);
        }
    }

    private void addCookies(ModelAndView modelAndView, HttpServletResponse response) {
        if (modelAndView.getCookies() != null && !modelAndView.getCookies().isEmpty()) {
            for (Cookie cookie : modelAndView.getCookies()) {
                response.addCookie(cookie);
            }
        }
    }

    private void addSessionAttributes(ModelAndView modelAndView, HttpServletRequest request) {
        if (modelAndView.getSessionAttributes() != null && !modelAndView.getSessionAttributes().isEmpty()) {
            HttpSession session = request.getSession();
            for (Map.Entry<String, Object> entry : modelAndView.getSessionAttributes().entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
        }
    }

    private void addAttributes(ModelAndView modelAndView, HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : modelAndView.getAttributes().entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}

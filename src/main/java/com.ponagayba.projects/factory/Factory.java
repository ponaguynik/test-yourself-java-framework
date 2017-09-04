package com.ponagayba.projects.factory;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.controller.HomePageController;
import com.ponagayba.projects.controller.admin.question.*;
import com.ponagayba.projects.controller.admin.user.*;
import com.ponagayba.projects.controller.test.*;
import com.ponagayba.projects.controller.user.*;
import com.ponagayba.projects.dao.test.QuestionDAO;
import com.ponagayba.projects.dao.test.QuestionDAOImpl;
import com.ponagayba.projects.dao.test.TestResultDAO;
import com.ponagayba.projects.dao.test.TestResultDAOImpl;
import com.ponagayba.projects.dao.user.RoleDAO;
import com.ponagayba.projects.dao.user.RoleDAOImpl;
import com.ponagayba.projects.dao.user.UserDAO;
import com.ponagayba.projects.dao.user.UserDAOImpl;
import com.ponagayba.projects.service.test.*;
import com.ponagayba.projects.service.user.RoleService;
import com.ponagayba.projects.service.user.RoleServiceImpl;
import com.ponagayba.projects.service.user.UserService;
import com.ponagayba.projects.service.user.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Factory {

    public static Controller getHomePageController() {
        return new HomePageController();
    }

    public static Controller getSignUpPageController() {
        return new SignUpPageController();
    }

    public static Controller getSignUpController() {
        return new SignUpController();
    }

    public static Controller getLoginPageController() {
        return new LoginPageController();
    }

    public static Controller getLoginController() {
        return new LoginControllerLegacy();
    }

    public static Controller getLogoutController() {
        return new LogoutController();
    }

    public static Controller getTestPageController() {
        return new TestPageController();
    }

    public static Controller getTestController() {
        return new TestController();
    }

    public static Controller getFinishTestController() {
        return new FinishTestController();
    }

    public static Controller getCancelAnswerTestController() {
        return new CancelAnswerTestController();
    }

    public static Controller getAnswerTestController() {
        return new AnswerTestController();
    }

    public static Controller getResultsPageController() {
        return new ResultsPageController();
    }

    public static Controller getAdminUsersController() {
        return new UsersPageController();
    }

    public static Controller getManageUserPageController() {
        return new ManageUserPageController();
    }

    public static Controller getDeleteUserController() {
        return new DeleteUserController();
    }

    public static Controller getUpdateUserController() {
        return new UpdateUserController();
    }

    public static Controller getUpdateUserRolesController() {
        return new UpdateUserRolesController();
    }

    public static Controller getQuestionsPageController() {
        return new QuestionsPageController();
    }

    public static Controller getAddQuestionPageController() {
        return new AddQuestionPageController();
    }

    public static Controller getAddQuestionController() {
        return new AddQuestionController();
    }


    public static UserService getUserService() {
        return new UserServiceImpl(getUserDAO(), getRoleDAO());
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl(getRoleDAO());
    }

    public static QuestionService getQuestionService() {
        return new QuestionServiceImpl(getQuestionDAO());
    }

    public static TestService getTestService() {
        return new TestServiceImpl();
    }

    public static TestResultService getTestResultService() {
        return new TestResultServiceImpl(getTestResultDAO());
    }


    private static RoleDAO getRoleDAO() {
        return new RoleDAOImpl(getConnection());
    }

    private static UserDAO getUserDAO() {
        return new UserDAOImpl(getConnection());
    }

    private static QuestionDAO getQuestionDAO() {
        return new QuestionDAOImpl(getConnection());
    }

    private static TestResultDAO getTestResultDAO() {
        return new TestResultDAOImpl(getConnection());
    }


    private static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (Exception e) {
            throw new RuntimeException("Driver has not been initialized");
        }
        return connection;
    }

    public static Controller getDeleteQuestionController() {
        return new DeleteQuestionController();
    }

    public static Controller getEditQuestionPageController() {
        return new EditQuestionPageController();
    }

    public static Controller getEditQuestionController() {
        return new EditQuestionController();
    }
}

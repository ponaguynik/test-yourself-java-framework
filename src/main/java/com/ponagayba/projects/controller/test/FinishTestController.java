package com.ponagayba.projects.controller.test;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.model.test.TestResult;
import com.ponagayba.projects.service.test.TestResultService;
import com.ponagayba.projects.service.test.TestService;
import com.ponagayba.projects.service.user.UserService;
import com.ponagayba.projects.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class FinishTestController implements Controller {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestResultService testResultService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView();
        Test test = (Test) request.getSession().getAttribute("test");
        if (!finishTestAnyway(request, result, testService)) {
            result.setView("test/test");
            return result;
        }
        TestResult testResult = testService.generateTestResult(test);
        result.setView("test/testResult");
        result.setAttribute("testResult", testResult);
        updateUserResults(testResult);
        request.getSession().setAttribute("test", null);

        return result;
    }

    private boolean finishTestAnyway(HttpServletRequest request, ModelAndView mv, TestService testService) {
        Test test = (Test) request.getSession().getAttribute("test");
        String finishTestAnyway = request.getParameter("finishTestAnyway");
        if (finishTestAnyway == null) {
            int numOfUnanswered = testService.numberOfUnansweredQuestions(test.getQuestions());
            if (numOfUnanswered != 0) {
                mv.setAttribute("finishMessage", "You have " + numOfUnanswered + " unanswered question(s). Do you want to finish the test anyway?");
                return false;
            }
        }
        return true;
    }

    private void updateUserResults(TestResult testResult) throws SQLException {
        User user = userService.findById(testResult.getUserId());
        userService.updateResults(user, testResult);
        testResultService.addTestResult(testResult);
    }
}

package com.ponagayba.projects.controller.test;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.service.test.QuestionService;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TestController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("test/test");
        if (request.getSession().getAttribute("test") == null) {
            prepareTest(request, result);
        }
        setCurrentQuestion(request, result);
        return result;
    }

    private void prepareTest(HttpServletRequest request, ModelAndView mv) throws SQLException {
        QuestionService questionService = Factory.getQuestionService();
        User user = (User) request.getAttribute("user");
        List<Question> questions = questionService.getAll();
        List<Question> randomQuestions = questionService.getRandomQuestions(questions, 10);
        Test test = new Test();
        test.setUserId(user.getId());
        test.setQuestions(randomQuestions);
        test.setStartTime(System.nanoTime());
        mv.setSessionAttribute("test", test);
    }

    private int getQuestionNum(HttpServletRequest request) {
        int qnNum;
        try {
            qnNum = Integer.parseInt(request.getParameter("qnNum"));
        } catch (NumberFormatException e) {
            try {
                qnNum = (int) request.getAttribute("qnNum");
            } catch (NullPointerException e1) {
                qnNum = 1;
            }
        }
        return qnNum;
    }

    private void setCurrentQuestion(HttpServletRequest request, ModelAndView mv) {
        int qnNum = getQuestionNum(request);
        HttpSession session = request.getSession();
        Test test = (Test) session.getAttribute("test");
        if (test == null) {
            test = (Test) mv.getSessionAttribute("test");
        }
        for (Question question : test.getQuestions()) {
            if (question.getNum() == qnNum) {
                question.setActive(true);
            } else {
                question.setActive(false);
            }
        }
        test.setCurrentQn(test.getQuestions().get(qnNum-1));
    }
}

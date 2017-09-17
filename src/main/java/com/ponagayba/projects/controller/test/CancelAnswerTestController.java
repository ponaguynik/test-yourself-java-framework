package com.ponagayba.projects.controller.test;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.service.test.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class CancelAnswerTestController {

    @Autowired
    private QuestionService questionService;

/*    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("test/test");
        Test test = (Test) request.getSession().getAttribute("test");
        Question currentQn = test.getCurrentQn();
        questionService.resetAnswers(currentQn);
        result.setAttribute("qnNum", currentQn.getNum());
        return result;
    }*/
}

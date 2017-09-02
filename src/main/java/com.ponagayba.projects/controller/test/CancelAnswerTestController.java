package com.ponagayba.projects.controller.test;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class CancelAnswerTestController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("test/test");
        Test test = (Test) request.getSession().getAttribute("test");
        Question currentQn = test.getCurrentQn();
        Factory.getQuestionService().resetAnswers(currentQn);
        result.setAttribute("qnNum", currentQn.getNum());
        return result;
    }
}

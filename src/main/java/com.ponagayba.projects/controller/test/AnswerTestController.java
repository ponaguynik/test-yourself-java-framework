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
import java.util.Arrays;
import java.util.List;

public class AnswerTestController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("test/test");
        String[] answers = request.getParameterValues("answer");
        if (answers == null) {
            result.setAttribute("message", "Choose at least one option.");
            return result;
        }

        Test test = (Test) request.getSession().getAttribute("test");
        Question currentQn = test.getCurrentQn();

        Factory.getQuestionService().processAnswers(currentQn, Arrays.asList(answers));
        setNextQuestion(test);

        return result;
    }

    private void setNextQuestion(Test test) {
        List<Question> questions = test.getQuestions();
        int nextNum = getNextQuestionNum(test);
        for (Question question : questions) {
            if (question.getNum() == nextNum) {
                question.setActive(true);
                test.setCurrentQn(question);
            } else {
                question.setActive(false);
            }
        }
    }

    private int getNextQuestionNum(Test test) {
        List<Question> questions = test.getQuestions();
        int nextNum = test.getCurrentQn().getNum() + 1;
        try {
            questions.get(nextNum-1);
        } catch (IndexOutOfBoundsException e) {
            nextNum = test.getCurrentQn().getNum();
        }
        return nextNum;
    }
}

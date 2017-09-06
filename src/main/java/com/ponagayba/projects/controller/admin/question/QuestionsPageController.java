package com.ponagayba.projects.controller.admin.question;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.service.test.QuestionService;
import com.ponagayba.projects.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class QuestionsPageController implements Controller {

    @Autowired
    private QuestionService questionService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        List<Question> questions = questionService.getAll();
        Collections.reverse(questions);
        result.setAttribute("questions", questions);
        result.setAttribute("page", "questions");
        return result;
    }
}

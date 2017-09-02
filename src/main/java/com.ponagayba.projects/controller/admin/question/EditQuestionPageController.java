package com.ponagayba.projects.controller.admin.question;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class EditQuestionPageController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        Question question = Factory.getQuestionService().findById(questionId);
        result.setAttribute("question", question);
        result.setAttribute("page", "editQuestion");
        return result;
    }
}

package com.ponagayba.projects.controller.admin.question;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.factory.Factory;
import com.ponagayba.projects.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteQuestionController implements Controller {

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        Factory.getQuestionService().deleteQuestion(questionId);
        result.setRedirect(true);
        result.setView("/admin/questions");
        return result;
    }
}

package com.ponagayba.projects.controller.admin.question;

import com.ponagayba.projects.controller.Controller;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.service.test.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class AddQuestionController implements Controller {

  /*  @Autowired
    private QuestionService questionService;

    @Override
    public ModelAndView process(HttpServletRequest request) throws ServletException, IOException, SQLException {
        ModelAndView result = new ModelAndView("admin/admin");
        String configured = request.getParameter("configured");
        if (configured == null) {
            configure(request, result);
            result.setAttribute("page", "addQuestion1");
        } else {
            Question question = parseQuestion(request);
            questionService.create(question);
            result.setRedirect(true);
            result.setView("/admin/questions");
        }
        return result;
    }

    private void configure(HttpServletRequest request, ModelAndView mv) {
        String withCode = request.getParameter("withCode");
        String optionType = request.getParameter("optionType");
        String optionsNum = request.getParameter("optionsNum");
        mv.setAttribute("withCode", withCode);
        mv.setAttribute("optionType", optionType);
        mv.setAttribute("optionsNum", optionsNum);
    }

    private Question parseQuestion(HttpServletRequest request) {
        String question = request.getParameter("question");
        String code = request.getParameter("code");
        List<String> options = parseOptions(request);
        String optionType = request.getParameter("optionType");
        List<String> answers = new ArrayList<>();
        String[] answersInput = request.getParameterValues("option");
        if (answersInput != null) {
            answers = Arrays.asList(answersInput);
        }
        return new Question(
                question,
                code,
                options,
                optionType,
                answers
        );
    }

    private List<String> parseOptions(HttpServletRequest request) {
        List<String> result = new ArrayList<>();
        int optionsNum = Integer.parseInt(request.getParameter("optionsNum"));
        for (int i = 1; i <= optionsNum; i++) {
            result.add(request.getParameter("option" + i));
        }
        return result;
    }*/
}

package com.ponagayba.projects.controller;

import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.service.test.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/questions")
public class QuestionAdminController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView questions() {
        ModelAndView mv = new ModelAndView("admin/questions");
        List<Question> questions = questionService.getAll();
        Collections.reverse(questions);
        mv.addObject("questions", questions);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addQuestionPage() {
        return new ModelAndView("admin/addQuestion", "question", new Question());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addQuestion(@ModelAttribute Question question,
                                    @RequestParam("options") String[] options,
                                    @RequestParam("correctAnswers") String[] answers,
                                    Locale locale) {
        ModelAndView mv = new ModelAndView("admin/addQuestion");
        setOptionsAndAnswers(question, options, answers);
        questionService.addQuestion(question);
        mv.addObject("message", messageSource.getMessage("question.success", null, locale));
        return mv;
    }

    private void setOptionsAndAnswers(Question question, String[] options, String[] answers) {
        question.setOptions(Arrays.asList(options));
        question.setCorrectAnswers(Arrays.asList(answers));
    }
}
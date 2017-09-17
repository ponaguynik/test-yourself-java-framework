package com.ponagayba.projects.controller.test;

import com.ponagayba.projects.model.test.Answer;
import com.ponagayba.projects.model.test.Question;
import com.ponagayba.projects.model.test.Test;
import com.ponagayba.projects.model.test.TestResult;
import com.ponagayba.projects.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
@RequestMapping("/test")
@SessionAttributes("test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("answer")
    public Answer addAnswer() {
        return new Answer();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String test(HttpSession session, Model model) {
        if (session.getAttribute("test") == null) {
            model.addAttribute("test", testService.prepareTest());
        }
        return redirectToQuestion(1);
    }

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public String changeQuestion(@RequestParam("num") int qnNum, @SessionAttribute Test test) {
        Question nextQn;
        try {
            nextQn = test.getQuestions().get(qnNum - 1);
        } catch (IndexOutOfBoundsException e) {
            return redirectToQuestion(test.getQuestions().size());
        }
        test.getCurrentQn().setActive(false);
        nextQn.setActive(true);
        test.setCurrentQn(nextQn);
        return "test/test";
    }

    @RequestMapping(value = "/question/answer", method = RequestMethod.POST)
    public String answerQuestion(@SessionAttribute Test test, @ModelAttribute Answer answer,
                                 Model model, Locale locale) {
        if (answer.getChosenOptions().isEmpty()) {
            model.addAttribute("answerError",
                    messageSource.getMessage("option.required", null, locale));
            return "test/test";
        } else {
            testService.answerQuestion(test.getCurrentQn(), answer);
            return redirectToQuestion(test.getCurrentQn().getNum());
        }
    }

    @RequestMapping(value = "/question/cancel", method = RequestMethod.GET)
    public String cancelQuestion(@SessionAttribute Test test) {
        testService.resetAnswer(test.getCurrentQn());
        return redirectToQuestion(test.getCurrentQn().getNum());
    }

    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String finishTest(@SessionAttribute Test test, Model model, SessionStatus sessionStatus) {
        TestResult testResult = testService.generateTestResult(test);
        model.addAttribute("testResult", testResult);
        sessionStatus.setComplete();
        return "test/testResult";
    }

    private String redirectToQuestion(int num) {
        return "redirect:/test/question?num=" + num;
    }
}

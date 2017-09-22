package com.ponagayba.projects.controller;

import com.ponagayba.projects.exception.AuthenticatedException;
import com.ponagayba.projects.exception.EmailExistsException;
import com.ponagayba.projects.exception.UsernameExistsException;
import com.ponagayba.projects.model.User;
import com.ponagayba.projects.model.test.TestResult;
import com.ponagayba.projects.service.test.TestResultService;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, Model model, Locale locale, Principal principal)
            throws AuthenticatedException {
        if (principal != null) {
            throw new AuthenticatedException();
        }
        if (error != null) {
            model.addAttribute("error", messageSource.getMessage("login.error", null, locale));
        }
        return "user/login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpPage(Model model, Principal principal) throws AuthenticatedException {
        if (principal != null) {
            throw new AuthenticatedException();
        }
        model.addAttribute("user", new User());
        return "user/signUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                         Model model, Principal principal)
            throws AuthenticatedException, EmailExistsException, UsernameExistsException {
        if (principal != null) {
            throw new AuthenticatedException();
        }
        if (bindingResult.hasErrors()) {
            return "user/signUp";
        }
        userService.addNewUser(user);
        model.addAttribute("message", "New user has been successfully created!");
        return "user/login";
    }

    @RequestMapping(value = "/user/results", method = RequestMethod.GET)
    public ModelAndView testResults(Principal principal) throws AuthenticationException {
        if (principal == null) {
            throw new AuthenticationException();
        }
        ModelAndView mv = new ModelAndView("user/results");
        User user = userService.getByUsername(principal.getName());
        List<TestResult> results = testResultService.getUserResults(user.getId());
        Collections.reverse(results);
        mv.addObject("results", results);
        return mv;
    }
}
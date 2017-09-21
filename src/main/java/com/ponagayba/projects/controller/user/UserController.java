package com.ponagayba.projects.controller.user;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, Model model, Locale locale) {
        if (error != null) {
            model.addAttribute("error", messageSource.getMessage("login.error", null, locale));
        }
        return "user/login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "user/signUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/signUp";
        }
        userService.addNewUser(user);
        model.addAttribute("message", "New user has been successfully created!");
        return "redirect:user/login";
    }
}
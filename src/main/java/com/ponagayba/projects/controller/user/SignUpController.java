package com.ponagayba.projects.controller.user;

import com.ponagayba.projects.model.User;
import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "user/signUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/signUp";
        }
        model.addAttribute("message", "New user has been successfully created!");
        return "redirect:user/login";
    }
}

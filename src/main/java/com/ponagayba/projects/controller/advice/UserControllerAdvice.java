package com.ponagayba.projects.controller.advice;

import com.ponagayba.projects.exception.AuthenticatedException;
import com.ponagayba.projects.exception.EmailExistsException;
import com.ponagayba.projects.exception.UsernameExistsException;
import com.ponagayba.projects.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import java.util.Locale;

@ControllerAdvice
public class UserControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView authenticationException(Locale locale) {
        ModelAndView mv = new ModelAndView("user/login");
        mv.addObject("error", messageSource.getMessage("login.login.first", null, locale));
        return mv;
    }

    @ExceptionHandler(AuthenticatedException.class)
    public String authenticatedException() {
        return "redirect:/";
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ModelAndView usernameExistsException(Locale locale) {
        ModelAndView mv = new ModelAndView("user/signUp");
        mv.addObject("error", messageSource.getMessage("user.username.exists", null, locale));
        mv.addObject("user", new User());
        return mv;
    }

    @ExceptionHandler(EmailExistsException.class)
    public ModelAndView emailExistsException(Locale locale) {
        ModelAndView mv = new ModelAndView("user/signUp");
        mv.addObject("error", messageSource.getMessage("user.email.exists", null, locale));
        mv.addObject("user", new User());
        return mv;
    }
}

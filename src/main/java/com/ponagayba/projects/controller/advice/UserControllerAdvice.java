package com.ponagayba.projects.controller.advice;

import com.ponagayba.projects.exception.AuthenticatedException;
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
    public ModelAndView authenticationHandler(Locale locale) {
        ModelAndView mv = new ModelAndView("user/login");
        mv.addObject("error", messageSource.getMessage("login.login.first", null, locale));
        return mv;
    }

    @ExceptionHandler(AuthenticatedException.class)
    public String authenticatedHandler() {
        return "redirect:/";
    }
}

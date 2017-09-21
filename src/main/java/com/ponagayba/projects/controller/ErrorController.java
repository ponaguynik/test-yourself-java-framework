package com.ponagayba.projects.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class ErrorController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView errorPage(HttpServletRequest request, Locale locale) {
        ModelAndView mv = new ModelAndView("error");
        int errorCode = getErrorCode(request);
        String msg;
        try {
            msg = messageSource.getMessage("error.code." + errorCode, null, locale);
        } catch (NoSuchMessageException e) {
            msg = messageSource.getMessage("error.code.default", null, locale);
        }
        if (errorCode != -1) {
            mv.addObject("errorCode", errorCode);
            mv.addObject("message", msg);
        } else {
            mv.addObject("errorCode", msg);
        }
        return mv;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        Object result = httpRequest
                .getAttribute("javax.servlet.error.status_code");
        return result != null ? (Integer) result : -1;
    }
}

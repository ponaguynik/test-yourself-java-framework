package com.ponagayba.projects.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
public class TestAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "&& execution(* com.ponagayba.projects.controller.TestController.*(..))")
    public Object testAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}

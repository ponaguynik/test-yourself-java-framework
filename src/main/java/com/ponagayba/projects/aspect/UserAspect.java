package com.ponagayba.projects.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
public class UserAspect {

    @Around("execution(* com.ponagayba.projects.controller.user.UserController.login(..)) " +
            "|| execution(* com.ponagayba.projects.controller.user.UserController.signUpPage(..))")
    public Object loginAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}

package com.ponagayba.projects.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsMatch {

    String message() default "Passwords don't match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

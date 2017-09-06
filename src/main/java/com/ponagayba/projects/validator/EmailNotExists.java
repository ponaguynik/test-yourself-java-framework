package com.ponagayba.projects.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailNotExistsValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotExists {

    String message() default "Email already registered.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

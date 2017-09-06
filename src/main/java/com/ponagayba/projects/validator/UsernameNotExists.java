package com.ponagayba.projects.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameNotExistsValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameNotExists {

    String message() default "{Username already exists.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

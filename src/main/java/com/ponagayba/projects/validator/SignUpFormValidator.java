package com.ponagayba.projects.validator;

import com.ponagayba.projects.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

public class SignUpFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
        User user = (User) o;
        if (!user.getUsername().matches("^[a-zA-Z]+$")) {
            errors.rejectValue("username", "username.en.required");
        } else if (checkLength(user.getUsername(), 4, 12)) {
            errors.rejectValue("username", "length.constraint", new Object[] {"Username", 4, 12},
                    "Length constraint");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confPassword", "confirm.password.required");

    }

    private boolean checkLength(String value, int min, int max) {
        return value.length() >= min && value.length() <= max;
    }
}

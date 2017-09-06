package com.ponagayba.projects.validator;

import com.ponagayba.projects.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.SQLException;

public class UsernameNotExistsValidator implements ConstraintValidator<UsernameNotExists, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return !userService.usernameExists(value);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

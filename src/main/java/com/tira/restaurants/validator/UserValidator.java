package com.tira.restaurants.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



import com.tira.restaurants.dto.UserRegisterDTO;
import com.tira.restaurants.service.UserService;

@Component
public class UserValidator implements Validator {
	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterDTO user = (UserRegisterDTO) o;

        if (user.getEmail().length() < 5 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Please use between 6 and 32 characters.");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Someone already has that username.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Try one with at least 8 characters.");
        }

    }
}

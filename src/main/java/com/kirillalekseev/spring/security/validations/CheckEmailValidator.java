package com.kirillalekseev.spring.security.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail ,String> {

    private String emailPattern;

    @Override
    public boolean isValid(String enteredValue, ConstraintValidatorContext constraintValidatorContext) {
        if (enteredValue == null||enteredValue.equals("")) {
            return false; // запрещаем пустые значения
        }

        Pattern pattern = Pattern.compile(emailPattern);
        return pattern.matcher(enteredValue).matches();
    }
    @Override
    public void initialize(CheckEmail checkEmail) {
        emailPattern = checkEmail.value();
    }
}

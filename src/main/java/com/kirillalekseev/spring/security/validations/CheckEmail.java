package com.kirillalekseev.spring.security.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface CheckEmail {
    public String value() default "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.[A-Za-z]{2,4}$";
    public String message() default "incorrect data email must be like 'example@email.com'";

    public Class<?>[] groups() default {};                   // Строки для правильно работы
    public Class<? extends Payload> [] payload() default{};  // Строки для правильно работы
}

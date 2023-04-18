package com.ipm_manager_ws.ipm_manager_app.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)

public @interface Password {
    String message() default "Password must be a minimum of 8 characters and contain at least one upper case letter, one lower case letter and a special character: !@#$%^&+= ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

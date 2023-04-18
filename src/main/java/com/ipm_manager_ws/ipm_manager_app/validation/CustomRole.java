package com.ipm_manager_ws.ipm_manager_app.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomRoleValidator.class)

public @interface CustomRole {
    String message() default "Valid Roles include 'ROLE_USER' and 'ROLE_ADMIN'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

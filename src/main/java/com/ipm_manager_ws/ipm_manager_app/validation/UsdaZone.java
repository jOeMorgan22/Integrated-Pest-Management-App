package com.ipm_manager_ws.ipm_manager_app.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsdaZoneValidator.class)
public @interface UsdaZone {
    
    String message() default "Usda Zone must follow integer-letter format ex: 7a";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package com.ipm_manager_ws.ipm_manager_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomRoleValidator implements ConstraintValidator<CustomRole, String>{

    private final  String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.isEmpty() || value.isBlank() || value == null){
            return false;
        }
        for(int i = 0; i < roles.length; i++){
            if(value.equals(roles[i])){
                return true;
            }
        }
        return false;
    }
    
}

package com.ipm_manager_ws.ipm_manager_app.validation;

    import jakarta.validation.ConstraintValidator;
    import jakarta.validation.ConstraintValidatorContext;

public class UsdaZoneValidator implements ConstraintValidator<UsdaZone, String> {

    private final String[] usdazones = {"0a", "0b", "1a", "1b", "2a", "2b", "3a", "3b", "4a", "4b", "5a", "5b",
     "6a", "6b", "7a", "7b", "8a", "8b", "9a", "9b", "10a", "10b", "11a", "11b", "12a", "12b", "13a", "13b"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.isEmpty() || value.isBlank() || value == null){
            return false;
        }
        for(int i = 0; i < usdazones.length; i++){
            if(value.equals(usdazones[i])){
                return true;
            }
        }
        return false;
    }
}
    

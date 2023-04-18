package com.ipm_manager_ws.ipm_manager_app.request_objects;

import com.ipm_manager_ws.ipm_manager_app.validation.CustomRole;
import com.ipm_manager_ws.ipm_manager_app.validation.Password;
import com.ipm_manager_ws.ipm_manager_app.validation.UsdaZone;
import com.ipm_manager_ws.ipm_manager_app.validation.Username;

import jakarta.validation.constraints.Email;

public record UserGroupCodeRegistrationDetails(String groupCode, @Username String username, @Email String email, 
                                @UsdaZone String usdaZone, @Password String password, @CustomRole String role) {   
}

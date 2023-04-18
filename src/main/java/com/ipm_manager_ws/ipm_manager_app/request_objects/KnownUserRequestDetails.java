package com.ipm_manager_ws.ipm_manager_app.request_objects;

import com.ipm_manager_ws.ipm_manager_app.validation.Username;

public record KnownUserRequestDetails(String groupCode, @Username String username) {
    
}

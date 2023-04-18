package com.ipm_manager_ws.ipm_manager_app.request_objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record GroupRequestDetails(@NotBlank @NotEmpty String groupName) {
    
}

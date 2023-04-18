package com.ipm_manager_ws.ipm_manager_app.request_objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ReportRequestDetails(@NotBlank(message = "Title cannot be blank") @NotEmpty String reportTitle,
                            @NotBlank(message = "Description cannot be blank") @NotEmpty String reportDescription) {
    
}

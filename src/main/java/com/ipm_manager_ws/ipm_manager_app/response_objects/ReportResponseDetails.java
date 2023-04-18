package com.ipm_manager_ws.ipm_manager_app.response_objects;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ipm_manager_ws.ipm_manager_app.entities.Action;

public record ReportResponseDetails(Long id, @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime date, boolean resolved, String groupName, String username,
                String reportTitle, String reportDescription, List<Action> actions) {
    
}

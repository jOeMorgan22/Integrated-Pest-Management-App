package com.ipm_manager_ws.ipm_manager_app.service;

import com.ipm_manager_ws.ipm_manager_app.entities.Action;

import java.util.List;

public interface ActionService {
        
        Action saveAction(Long reportId, Action action);
        Action getActionById(Long id);
        void deleteAction(Long id);
        List<Action> getActions();
        List<Action> getActionsByReportId(Long reportId);
        Action updateAction(Long actionId, String title, String description);

    }

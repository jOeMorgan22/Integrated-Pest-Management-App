package com.ipm_manager_ws.ipm_manager_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.ipm_manager_ws.ipm_manager_app.entities.Action;

import java.util.List;


    public interface ActionRepository extends CrudRepository<Action, Long>{
        List<Action> findByReportId(Long reportId);
    
    }

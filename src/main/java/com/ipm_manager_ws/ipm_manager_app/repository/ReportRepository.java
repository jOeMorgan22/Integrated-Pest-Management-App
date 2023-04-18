package com.ipm_manager_ws.ipm_manager_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.ipm_manager_ws.ipm_manager_app.entities.Report;

import java.util.List;


    public interface ReportRepository extends CrudRepository<Report, Long>{ 
        List<Report> findByUserId(Long userId);
        List<Report> findByGroupCode(String groupCode);
        
    }

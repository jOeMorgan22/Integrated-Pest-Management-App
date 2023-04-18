package com.ipm_manager_ws.ipm_manager_app.service;

import com.ipm_manager_ws.ipm_manager_app.entities.Report;
import com.ipm_manager_ws.ipm_manager_app.response_objects.ReportResponseDetails;

import java.util.List;

public interface ReportService {
        Report saveReport(Long userId, Report report);
        Report getReportById(Long id);
        void deleteReport(Long id);
        List<Report> getReports();
        List<Report> getReportsByUserId(Long id);
        Report updateReport(Long reportId, String title, String desription);
        Report resolveReport(Long reportId);
        List<Report> getReportsByGroupCode(String groupCode);
        ReportResponseDetails mapReportToResponseObj(Report report);
    
    }

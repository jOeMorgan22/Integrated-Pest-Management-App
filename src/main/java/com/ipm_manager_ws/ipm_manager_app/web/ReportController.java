package com.ipm_manager_ws.ipm_manager_app.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipm_manager_ws.ipm_manager_app.request_objects.ReportRequestDetails;
import com.ipm_manager_ws.ipm_manager_app.response_objects.ReportResponseDetails;
import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.entities.Report;
import com.ipm_manager_ws.ipm_manager_app.service.IpmUserService;
import com.ipm_manager_ws.ipm_manager_app.service.ReportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    private final IpmUserService userService;

    public ReportController(ReportService reportService, IpmUserService userService){
        this.reportService= reportService;
        this.userService = userService;
    }

    @GetMapping("/group-reports")
    public ResponseEntity<List<ReportResponseDetails>> getReportsByGroupCode(Authentication authentication){
        IpmUser user = userService.getUserByUsername(authentication.getPrincipal().toString());
        List<Report> userReports = reportService.getReportsByGroupCode(user.getGroupCode());
        List<ReportResponseDetails> responseObjs = new ArrayList<>();
        for(Report report : userReports){
            responseObjs.add(reportService.mapReportToResponseObj(report));
        }
        return new ResponseEntity<>(responseObjs, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReportResponseDetails>> getReportsByUserId(@PathVariable Long userId){
        List<Report> userReports = reportService.getReportsByUserId(userId);
        List<ReportResponseDetails> responseObjs = new ArrayList<>();
        for(Report report : userReports){
            responseObjs.add(reportService.mapReportToResponseObj(report));
        }
        return new ResponseEntity<>(responseObjs, HttpStatus.OK);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportResponseDetails> getReport(@PathVariable Long reportId){
        return new ResponseEntity<>(reportService.mapReportToResponseObj(reportService.getReportById(reportId)), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<ReportResponseDetails> createReport(@PathVariable Long userId, @Valid @RequestBody ReportRequestDetails reportRequest){
        Report report = new Report();
        report.setReportTitle(reportRequest.reportTitle());
        report.setReportDescription(reportRequest.reportDescription());
        Report savedreport = reportService.saveReport(userId, report);
        return new ResponseEntity<>(reportService.mapReportToResponseObj(savedreport),HttpStatus.CREATED);
    }
    @PostMapping("/{reportId}")
    public ResponseEntity<ReportResponseDetails> resolveReport(@PathVariable Long reportId){
        return new ResponseEntity<>(reportService.mapReportToResponseObj(reportService.resolveReport(reportId)), HttpStatus.OK);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<HttpStatus> deleteReport(@PathVariable Long reportId){
        reportService.deleteReport(reportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<ReportResponseDetails> updateReport(@PathVariable Long reportId, @RequestBody Report report){
        Report updatedReport = reportService.updateReport(reportId, report.getReportTitle(), report.getReportDescription());
    return new ResponseEntity<>(reportService.mapReportToResponseObj(updatedReport), HttpStatus.OK);
    }
    
}

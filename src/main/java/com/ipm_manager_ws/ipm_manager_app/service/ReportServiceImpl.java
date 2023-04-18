package com.ipm_manager_ws.ipm_manager_app.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.entities.Report;
import com.ipm_manager_ws.ipm_manager_app.exceptions.ReportNotFoundException;
import com.ipm_manager_ws.ipm_manager_app.exceptions.GroupNotFoundException;
import com.ipm_manager_ws.ipm_manager_app.exceptions.UserNotFoundException;
import com.ipm_manager_ws.ipm_manager_app.repository.ReportRepository;
import com.ipm_manager_ws.ipm_manager_app.response_objects.ReportResponseDetails;


    @Service
    public class ReportServiceImpl implements ReportService{

        
        private final ReportRepository reportRepository;

        private final IpmUserService userService;

        public ReportServiceImpl(ReportRepository reportRepository, IpmUserService userService){
            this.reportRepository = reportRepository;
            this.userService = userService;
        }

        @Override
        public void deleteReport(Long id) {
            reportRepository.deleteById(id);
        }

        @Override
        public Report getReportById(Long id) throws ReportNotFoundException{
            Optional<Report> report = reportRepository.findById(id);
            if(report.isPresent()){
                return report.get();
            }else{
                throw new ReportNotFoundException(id);
            }
        }

        @Override
        public List<Report> getReportsByUserId(Long userId) throws ReportNotFoundException{
            List<Report> reports = (List<Report>)reportRepository.findByUserId(userId);
            if(reports.isEmpty()){
                throw new ReportNotFoundException();
            }
            return reports;
        }

        @Override
        public List<Report> getReports() throws ReportNotFoundException{
            List<Report> reports = (List<Report>)reportRepository.findAll();
            if(reports.isEmpty()){
                throw new ReportNotFoundException();
            }
            return reports;
        }

        @Override
        public Report saveReport(Long userId, Report report) {
            try{
                IpmUser user = userService.getUser(userId);
                return reportRepository.save(new Report(
                    user.getGroupCode(),
                    report.getReportTitle(),
                    report.getReportDescription(),
                    user)
                    );
            }catch(UserNotFoundException e){
                throw new UserNotFoundException(userId);
            }
        }

        @Override
        public Report updateReport(Long reportId, String title, String description) throws ReportNotFoundException{
            try{
                Report report = getReportById(reportId);
                report.setReportTitle(title);
                report.setReportDescription(description);
                report.setDate(LocalDateTime.now());
                return reportRepository.save(report);
            }catch(UserNotFoundException e){
                throw new UserNotFoundException(reportId);
            }
        }   

        public Report resolveReport(Long reportId) throws ReportNotFoundException{
           Report report =  getReportById(reportId);
           report.setResolved(true);
           return reportRepository.save(report);
        }

        @Override
        public List<Report> getReportsByGroupCode(String groupCode) {
            try{
                List<Report> groupReports = (List<Report>)reportRepository.findByGroupCode(groupCode);
                return groupReports;
            }catch(GroupNotFoundException ex){
                throw new GroupNotFoundException();
            }
        }

        @Override
        public ReportResponseDetails mapReportToResponseObj(Report report) {
            return new ReportResponseDetails(
                report.getId(), 
                report.getDate(),
                report.isResolved(),
                report.getUser().getGroupName(),
                report.getUser().getUsername(),  
                report.getReportTitle(), 
                report.getReportDescription(), 
                report.getAction());
        }
    
    }

package com.ipm_manager_ws.ipm_manager_app.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ipm_manager_ws.ipm_manager_app.entities.Action;
import com.ipm_manager_ws.ipm_manager_app.entities.Report;
import com.ipm_manager_ws.ipm_manager_app.exceptions.ActionNotFoundException;
import com.ipm_manager_ws.ipm_manager_app.exceptions.ReportNotFoundException;
import com.ipm_manager_ws.ipm_manager_app.repository.ActionRepository;


@Service
public class ActionServiceImpl implements ActionService{

    private final ActionRepository actionRepository;

    private final ReportService reportService;

    public ActionServiceImpl(ActionRepository actionRepository, ReportService reportService){
        this.actionRepository = actionRepository;
        this.reportService = reportService;
    }
    
    @Override
    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = (List<Action>)actionRepository.findAll();
        if(actions.isEmpty()){
            throw new ActionNotFoundException();
        }
        return actions;
    }

    @Override
    public Action getActionById(Long id) {
        Optional<Action> action = actionRepository.findById(id);
        if(action.isPresent()){
            return action.get();
        }else{
            throw new ActionNotFoundException(id);
        }   
    }

    @Override
    public List<Action> getActionsByReportId(Long reportId) {
        List<Action> actions = actionRepository.findByReportId(reportId);
            if(actions.isEmpty()){
            throw new ActionNotFoundException();
        }
        return actions;
    }

    @Override
    public Action saveAction(Long reportId, Action action) {
        try{
            Report report = reportService.getReportById(reportId);
            action.setReport(report);
            action.setDate(LocalDateTime.now());
            return actionRepository.save(action);
        }catch(ReportNotFoundException e){
            throw new ReportNotFoundException(reportId);
        }
    }

    @Override
    public Action updateAction(Long actionId, String title, String description){
        try{
            Action action = getActionById(actionId);
            action.setActionTitle(title);
            action.setActionDescription(description);
            action.setDate(LocalDateTime.now());
            return actionRepository.save(action);
        }catch(ActionNotFoundException e){
            throw new ActionNotFoundException(actionId);
        }
    } 
        
        
    

    

    
    
}

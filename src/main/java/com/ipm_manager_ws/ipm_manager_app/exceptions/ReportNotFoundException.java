package com.ipm_manager_ws.ipm_manager_app.exceptions;

public class ReportNotFoundException extends RuntimeException{

    public ReportNotFoundException(Long id){
        super(String.format("The report id '%d' does not exist in our database.", id));
    }

    public ReportNotFoundException(){
        super("No reports found");
    }
}

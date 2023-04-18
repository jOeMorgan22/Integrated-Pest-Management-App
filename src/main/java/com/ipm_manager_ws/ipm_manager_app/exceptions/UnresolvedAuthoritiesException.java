package com.ipm_manager_ws.ipm_manager_app.exceptions;

public class UnresolvedAuthoritiesException extends RuntimeException{
    
    public UnresolvedAuthoritiesException(){
        super("No resolved authorities, please check role");
    }
}

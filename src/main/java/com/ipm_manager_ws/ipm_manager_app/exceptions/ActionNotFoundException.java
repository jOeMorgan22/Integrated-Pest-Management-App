package com.ipm_manager_ws.ipm_manager_app.exceptions;

public class ActionNotFoundException extends RuntimeException{
    
    public ActionNotFoundException(Long id){
        super(String.format("The action id '%d' does not exist in our database.", id));
    }

    public ActionNotFoundException(){
        super("No actions were found");
    }

}

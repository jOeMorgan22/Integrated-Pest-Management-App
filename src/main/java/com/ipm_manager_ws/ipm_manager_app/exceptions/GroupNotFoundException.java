package com.ipm_manager_ws.ipm_manager_app.exceptions;

public class GroupNotFoundException extends RuntimeException{
    
    public GroupNotFoundException(){
        super("Group not found");
    }
}

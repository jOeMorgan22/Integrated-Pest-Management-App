package com.ipm_manager_ws.ipm_manager_app.exceptions;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(Long id){
        super(String.format("User '%d' was not found.", id));
    }

    public UserNotFoundException(String username){
        super(String.format("User '%s' was not found.", username));
    }
    
    public UserNotFoundException(){
        super(String.format("User was not found."));
    }
}

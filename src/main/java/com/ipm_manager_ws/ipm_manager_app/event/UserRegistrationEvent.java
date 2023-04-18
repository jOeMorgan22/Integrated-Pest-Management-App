package com.ipm_manager_ws.ipm_manager_app.event;

import org.springframework.context.ApplicationEvent;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;

public class UserRegistrationEvent extends ApplicationEvent {

    private static final long serialVersionUID = -2685172945219633123L;

    private IpmUser ipmUser;

    public UserRegistrationEvent(IpmUser ipmUser) {
        super(ipmUser);
        this.ipmUser = ipmUser;
    }

    public IpmUser getUser(){
        return ipmUser;
    }
    
    
}

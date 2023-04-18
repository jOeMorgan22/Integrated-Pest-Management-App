package com.ipm_manager_ws.ipm_manager_app.service;

import java.util.List;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;

    public interface IpmUserService{
        IpmUser getUser(Long id);
        IpmUser getUserByUsername(String username);
        IpmUser saveUser(IpmUser user);
        String checkUsernameAvailability(String username);
        void deleteUser(String username);
        List<IpmUser> getUsers(); 
        List<IpmUser> getUsersByGroupName(String groupName);
        List<IpmUser> getUsersByGroupCode(String groupCode);   
        String generateStringForGroupCode();    
    
}

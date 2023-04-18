package com.ipm_manager_ws.ipm_manager_app.service;

import com.ipm_manager_ws.ipm_manager_app.entities.UserGroup;

    public interface GroupService{
        UserGroup findGroupByGroupCode(String groupCode);
        UserGroup findGroupByGroupName(String groupName);
        UserGroup saveUserGroup(UserGroup group);
        void deleteUserGroup(UserGroup group);
        public String generateStringForGroupCode();
    
}

package com.ipm_manager_ws.ipm_manager_app.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ipm_manager_ws.ipm_manager_app.entities.UserGroup;
import com.ipm_manager_ws.ipm_manager_app.exceptions.GroupNotFoundException;
import com.ipm_manager_ws.ipm_manager_app.repository.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public UserGroup findGroupByGroupCode(String groupCode) {
        Optional<UserGroup> userGroup = groupRepository.findByGroupCode(groupCode);
        if(userGroup.isPresent()){
            return userGroup.get();
        }
        throw new GroupNotFoundException();
    }

    @Override
    public UserGroup findGroupByGroupName(String groupName) {
        Optional<UserGroup> userGroup = groupRepository.findByGroupName(groupName);
        if(userGroup.isPresent()){
            return userGroup.get();
        }
        throw new GroupNotFoundException();
    }

    @Override
    public UserGroup saveUserGroup(UserGroup group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteUserGroup(UserGroup group) {
        groupRepository.delete(group);
    }

    @Override
    public String generateStringForGroupCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
        
    }
    
}

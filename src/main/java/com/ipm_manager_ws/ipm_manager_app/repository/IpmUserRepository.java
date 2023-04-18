package com.ipm_manager_ws.ipm_manager_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;


    public interface IpmUserRepository extends CrudRepository<IpmUser, Long>{
        Optional<IpmUser> findByUsername(String username);
        List<IpmUser> findByGroupName(String groupName);
        List<IpmUser> findByGroupCode(String groupCode);
    }
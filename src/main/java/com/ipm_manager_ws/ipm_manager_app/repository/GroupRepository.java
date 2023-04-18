package com.ipm_manager_ws.ipm_manager_app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ipm_manager_ws.ipm_manager_app.entities.UserGroup;

public interface GroupRepository extends CrudRepository<UserGroup, Long>{
    Optional<UserGroup> findByGroupName(String groupName);
    Optional<UserGroup> findByGroupCode(String groupCode);
}

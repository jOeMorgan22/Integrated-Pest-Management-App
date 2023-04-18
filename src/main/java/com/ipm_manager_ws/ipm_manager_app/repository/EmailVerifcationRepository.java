package com.ipm_manager_ws.ipm_manager_app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ipm_manager_ws.ipm_manager_app.entities.EmailVerification;


    public interface EmailVerifcationRepository extends CrudRepository<EmailVerification,String>{
        Optional<EmailVerification> findByUsername(String username);
        boolean existsByUsername(String username);
    }

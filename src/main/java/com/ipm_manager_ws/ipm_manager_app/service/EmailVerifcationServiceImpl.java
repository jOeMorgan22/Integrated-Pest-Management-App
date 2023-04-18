package com.ipm_manager_ws.ipm_manager_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ipm_manager_ws.ipm_manager_app.entities.EmailVerification;
import com.ipm_manager_ws.ipm_manager_app.repository.EmailVerifcationRepository;

    @Service
    public class EmailVerifcationServiceImpl implements EmailVerificationService{

        private final EmailVerifcationRepository verificationRepository;

        public EmailVerifcationServiceImpl(EmailVerifcationRepository verifcationRepository){
            this.verificationRepository = verifcationRepository;
        }

        @Override
        public String generateVerification(String username) {
            if(!verificationRepository.existsByUsername(username)){
                EmailVerification verification = new EmailVerification(username);
                verificationRepository.save(verification);
                return verification.getVerificationId();
            }
            return getVerificationIdByUsername(username);
        }

        @Override
        public String getUsernameForVerificationId(String verificationId) {
            Optional<EmailVerification> verification = verificationRepository.findById(verificationId);
            if(verification.isPresent()){
                return verification.get().getVerificationId();
            }
            return null;
        }

        @Override
        public String getVerificationIdByUsername(String username) {
            Optional<EmailVerification> verification = verificationRepository.findByUsername(username);
            if(verification.isPresent()){
                return verification.get().getVerificationId();
            }
            return null;
        }
        
}

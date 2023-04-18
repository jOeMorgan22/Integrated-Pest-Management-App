package com.ipm_manager_ws.ipm_manager_app.service;

    public interface EmailVerificationService {
        String generateVerification(String username);
        String getVerificationIdByUsername(String username);
        String getUsernameForVerificationId(String verificationId);
}

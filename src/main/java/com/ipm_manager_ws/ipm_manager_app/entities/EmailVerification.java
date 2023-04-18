package com.ipm_manager_ws.ipm_manager_app.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Email_Verifications")
public class EmailVerification {
    
    @Id
    @GeneratedValue(generator = "UUID_Generator", strategy = GenerationType.UUID)
    @Column(name = "verification_Id")
    private String verificationId;

    @Column(name = "verifcation_usernames")
    private String username;

    public EmailVerification() {
    }

    public EmailVerification(String username) {
        this.username = username;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}

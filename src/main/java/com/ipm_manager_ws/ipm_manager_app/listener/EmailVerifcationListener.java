package com.ipm_manager_ws.ipm_manager_app.listener;

import java.util.Base64;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.event.UserRegistrationEvent;
import com.ipm_manager_ws.ipm_manager_app.service.EmailVerificationService;

@Service
public class EmailVerifcationListener implements 
        ApplicationListener<UserRegistrationEvent>{

    private final JavaMailSender mailSender;

    private final EmailVerificationService verificationService;

    public EmailVerifcationListener(JavaMailSender mailSender, EmailVerificationService verificationService) {
        this.mailSender = mailSender;
        this.verificationService = verificationService;
    }


    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        IpmUser user = event.getUser();
        String username = user.getUsername();
        String verifcationId = verificationService.generateVerification(username);
        String email = user.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("IPM Manager account verification");
        message.setText(getText(user, verifcationId));
        message.setTo(email);
        mailSender.send(message);
    }

    private String getText(IpmUser user, String verificationId) {
        String encodedVerificationId = new String(Base64.getEncoder().encode(verificationId.getBytes()));
        StringBuffer buffer = new StringBuffer();
        buffer.append("Hey ").append(user.getUsername())
        .append(",").append(System.lineSeparator()).append(System.lineSeparator());
        buffer.append("Your account has been successfully created in the IPM Manager application. ");
        buffer.append("Activate your account by clicking the following link: http://localhost:8080/verify/email?id=")
        .append(encodedVerificationId); 
        buffer.append(System.lineSeparator()).append(System.lineSeparator());
        buffer.append("Thanks,").append(System.lineSeparator()).append("IPM Manager Team");
        return buffer.toString();
    }
    
}

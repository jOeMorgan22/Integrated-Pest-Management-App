package com.ipm_manager_ws.ipm_manager_app.web;


import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.service.EmailVerificationService;
import com.ipm_manager_ws.ipm_manager_app.service.IpmUserService;

/* Current functionality of this controller is purely proof of concept. Emails will be sent with verification link
 * but verification is not necessary for application use. Without proper certificates and only http communication,
 * many email providers filter out email.
 */

@RestController
public class EmailVerificationController {

    private EmailVerificationService verificationService;

    private IpmUserService userService;

    public EmailVerificationController(EmailVerificationService verificationService, IpmUserService userService) {
        this.verificationService = verificationService;
        this.userService = userService;
    }

    @GetMapping("/verify/email")
    public ResponseEntity<HttpStatus> verifyEmail(@RequestParam String id){
        byte[] actualId = Base64.getDecoder().decode(id.getBytes());
        String username = verificationService.getUsernameForVerificationId(new String(actualId));
        if(username != null){
            IpmUser user = userService.getUserByUsername(username);
            user.setVerified(true);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}

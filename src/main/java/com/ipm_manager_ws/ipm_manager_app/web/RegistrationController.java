package com.ipm_manager_ws.ipm_manager_app.web;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.entities.UserGroup;
import com.ipm_manager_ws.ipm_manager_app.event.UserRegistrationEvent;
import com.ipm_manager_ws.ipm_manager_app.request_objects.UserGroupCodeRegistrationDetails;
import com.ipm_manager_ws.ipm_manager_app.request_objects.GroupRequestDetails;
import com.ipm_manager_ws.ipm_manager_app.request_objects.KnownUserRequestDetails;
import com.ipm_manager_ws.ipm_manager_app.request_objects.UserRegistrationRequestDetails;
import com.ipm_manager_ws.ipm_manager_app.response_objects.UserResponseDetails;
import com.ipm_manager_ws.ipm_manager_app.response_objects.GroupResponseDetails;
import com.ipm_manager_ws.ipm_manager_app.service.GroupService;
import com.ipm_manager_ws.ipm_manager_app.service.IpmUserService;

import jakarta.validation.Valid;

@RestController
public class RegistrationController {

    private IpmUserService userService;

    private GroupService groupService;

    private PasswordEncoder passwordEncoder;

    private ApplicationEventPublisher eventPublisher;

    public RegistrationController(IpmUserService userService, PasswordEncoder passwordEncoder, GroupService groupService, ApplicationEventPublisher eventPublisher){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.groupService = groupService;
        this.eventPublisher = eventPublisher;
    }
    

    @PostMapping("/register")
    public ResponseEntity<UserResponseDetails> createUser(@Valid @RequestBody UserRegistrationRequestDetails user){
        String availability = userService.checkUsernameAvailability(user.username());
        if(availability == null){
            IpmUser userRegistration = new IpmUser(
                user.username(),
                user.email(),
                user.usdaZone(),
                passwordEncoder.encode(user.password()),
                user.role()
                );
                userRegistration.setVerified(false);
                userService.saveUser(userRegistration);
                eventPublisher.publishEvent(new UserRegistrationEvent(userRegistration));
            return new ResponseEntity<>(new UserResponseDetails(
                userRegistration.getId(),
                userRegistration.getGroupName(),
                userRegistration.getUsername()),
                HttpStatus.CREATED
                );
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create-group")
    public ResponseEntity<GroupResponseDetails> createGroup(@Valid @RequestBody GroupRequestDetails groupRequestDetails){
        UserGroup group = new UserGroup(groupRequestDetails.groupName());
        group.setGroupCode(groupService.generateStringForGroupCode());
        groupService.saveUserGroup(group);
        return new ResponseEntity<>(new GroupResponseDetails(group.getGroupName(), group.getGroupCode()), HttpStatus.CREATED);
    }

    @PostMapping("/register-with-group-code")
    public ResponseEntity<UserResponseDetails> createUserWithGroupCode(@Valid @RequestBody UserGroupCodeRegistrationDetails user){
        String availability = userService.checkUsernameAvailability(user.username());
        UserGroup group = groupService.findGroupByGroupCode(user.groupCode());

        if(availability == null){
            IpmUser userRegistration = new IpmUser(
                user.username(),
                user.email(),
                user.usdaZone(),
                passwordEncoder.encode(user.password()),
                user.role()
                );
                userRegistration.setGroupName(group.getGroupName());
                userRegistration.setGroupCode(group.getGroupCode());
                userRegistration.setVerified(false);
                userService.saveUser(userRegistration);
                eventPublisher.publishEvent(new UserRegistrationEvent(userRegistration));
            return new ResponseEntity<UserResponseDetails>(new UserResponseDetails(
                userRegistration.getId(),
                userRegistration.getGroupName(),
                userRegistration.getUsername()),
                HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/join-group")
    public ResponseEntity<UserResponseDetails> joinGroup(@Valid @RequestBody KnownUserRequestDetails userRequest){
        UserGroup group = groupService.findGroupByGroupCode(userRequest.groupCode());
        IpmUser user = userService.getUserByUsername(userRequest.username());
        user.setGroupName(group.getGroupName());
        user.setGroupCode(group.getGroupCode());
        userService.saveUser(user);
        return new ResponseEntity<UserResponseDetails>(new UserResponseDetails(
            user.getId(),
            user.getGroupName(),
            user.getUsername()),
            HttpStatus.OK);
    }

    /* 
    *
    *   PlaceHolder of representative template
    *
    *   @GetMapping("/login-error")
    *   public ResponseEntity<String> invalidLogin(){
    *       String invalidCredentials = "Invalid username or password";
    *       return new ResponseEntity<>(invalidCredentials, HttpStatus.UNAUTHORIZED);
    *    }
    */
    
}

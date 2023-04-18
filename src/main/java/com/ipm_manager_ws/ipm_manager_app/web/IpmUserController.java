package com.ipm_manager_ws.ipm_manager_app.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.response_objects.UserResponseDetails;
import com.ipm_manager_ws.ipm_manager_app.service.IpmUserService;

@RestController
@RequestMapping("/users")
public class IpmUserController {

    private final IpmUserService userService;

    public IpmUserController(IpmUserService userService){
        this.userService= userService;
    }
    
    @GetMapping("/login")
    public ResponseEntity<UserResponseDetails> getUser(Authentication authentication){
        IpmUser user = userService.getUserByUsername(authentication.getName());
        return new ResponseEntity<>(new UserResponseDetails(
            user.getId(),
            user.getGroupName(),
            user.getUsername()),
            HttpStatus.OK
        );
    }

    @GetMapping("/group")
    public ResponseEntity<List<UserResponseDetails>> getUsersByGroupId(Authentication authentication){
        IpmUser ipmUser = userService.getUserByUsername(authentication.getName());
        List<IpmUser> groupUsers = userService.getUsersByGroupCode(ipmUser.getGroupCode());
        List<UserResponseDetails> groupUsersRespsonseObect= new ArrayList<>();

        for(IpmUser user : groupUsers){
            groupUsersRespsonseObect.add(new UserResponseDetails(
                user.getId(),
                user.getGroupName(), 
                user.getUsername())
            );
        }
        return new ResponseEntity<>(groupUsersRespsonseObect, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteUser(Authentication authentication){
        userService.deleteUser(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

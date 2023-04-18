package com.ipm_manager_ws.ipm_manager_app.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;

import com.ipm_manager_ws.ipm_manager_app.entities.Action;
import com.ipm_manager_ws.ipm_manager_app.service.ActionService;

    @RestController
    @RequestMapping("/actions")
    public class ActionController {

        private final ActionService actionService;

        public ActionController(ActionService actionService){
            this.actionService= actionService;
        }

        @GetMapping("/{actionId}")
        public ResponseEntity<Action> getAction(@PathVariable Long actionId){
            return new ResponseEntity<>(actionService.getActionById(actionId), HttpStatus.OK);
        }

        @GetMapping("/reports/{reportId}")
        public ResponseEntity<List<Action>> getActionByReportId(@PathVariable Long reportId){
            return new ResponseEntity<>(actionService.getActionsByReportId(reportId), HttpStatus.OK);
        } 

        /* Using Authentication here to allow a different username from the corresponding report */
        @PostMapping("/reports/{reportId}")
        public ResponseEntity<Action> createAction(Authentication authentication, @PathVariable Long reportId, @Valid @RequestBody Action action){
            action.setUsername(authentication.getPrincipal().toString());
            return new ResponseEntity<>(actionService.saveAction(reportId, action), HttpStatus.CREATED);
        }

        @DeleteMapping("/{actionId}")
        public ResponseEntity<HttpStatus> deleteAction(@PathVariable Long actionId){
            actionService.deleteAction(actionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping("/{actionId}")
        public ResponseEntity<Action> updateAction(@PathVariable Long actionId, @RequestBody Action action){
            return new ResponseEntity<>(actionService.updateAction(actionId, action.getActionTitle(), action.getActionDescription()), HttpStatus.OK);
        }
    }

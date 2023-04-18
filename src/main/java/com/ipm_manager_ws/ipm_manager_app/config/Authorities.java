package com.ipm_manager_ws.ipm_manager_app.config;

import java.util.Arrays;
import java.util.List;

public class Authorities {

    /* Non Functional. Would assign authorities based on role at registration however, 
     * roles alone provide minute enough scrutiny for purpose of this application.
     */
    
    public static final List<String> ADMIN_AUTHORITIES = Arrays.asList("READ", "WRITE", "UPDATE", "RESOLVE", "DELETE");

    public static final List<String> USER_AUTHORITIES = Arrays.asList("READ", "WRITE", "UPDATE");
    
}

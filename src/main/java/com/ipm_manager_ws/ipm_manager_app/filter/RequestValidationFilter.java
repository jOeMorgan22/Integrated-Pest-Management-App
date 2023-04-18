package com.ipm_manager_ws.ipm_manager_app.filter;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ipm_manager_ws.ipm_manager_app.exceptions.UserNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestValidationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);

        }catch(UserNotFoundException ex){
            throw new UserNotFoundException();
        }catch(BadCredentialsException ex){
            throw new BadCredentialsException("Invalid password");
        }catch(IOException ex){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    
    
}

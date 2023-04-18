package com.ipm_manager_ws.ipm_manager_app.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.repository.IpmUserRepository;


@Component
public class IpmUsernamePwdAuthenticationProvider implements AuthenticationProvider{

    private IpmUserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public IpmUsernamePwdAuthenticationProvider(IpmUserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<IpmUser> user = userRepository.findByUsername(authentication.getName());
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        // if(passwordEncoder.matches(password, user.get().getPassword()) && user.get().isVerified() != true){
        //     throw new DisabledException("Account not verified");
        // }
        if(passwordEncoder.matches(password, user.get().getPassword())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.get().getRole()));
            return new UsernamePasswordAuthenticationToken(username, null, 
                authorities);
        }else{
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    
}
 
package com.ipm_manager_ws.ipm_manager_app.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.repository.IpmUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    private IpmUserRepository userRepository;

    public JpaUserDetailsService(IpmUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {       
        IpmUser ipmUser = unwrapUser(username);
        String password = ipmUser.getPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ipmUser.getRole()));
        //return new User(username, password, authorities);
        return User.withUsername(username)
                    .password(password)
                    .authorities(authorities)
                    .disabled(!ipmUser.isVerified())
                    .build();
    }

    public IpmUser unwrapUser(String username) throws UsernameNotFoundException {
        Optional<IpmUser> ipmUser = userRepository.findByUsername(username);
        if(ipmUser.isPresent()){
            return ipmUser.get();
        }else{
            throw new UsernameNotFoundException("Username not found:" + username);
        }
    }

    
}

package com.ipm_manager_ws.ipm_manager_app.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipm_manager_ws.ipm_manager_app.config.Authorities;
import com.ipm_manager_ws.ipm_manager_app.entities.IpmUser;
import com.ipm_manager_ws.ipm_manager_app.exceptions.UserNotFoundException;
import com.ipm_manager_ws.ipm_manager_app.repository.IpmUserRepository;

    @Service
    public class IpmUserServiceImpl implements IpmUserService{

        private final IpmUserRepository userRepository;

        public IpmUserServiceImpl(IpmUserRepository userRepository){
            this.userRepository = userRepository;
        }

        @Override
        public void deleteUser(String username)throws UserNotFoundException{
            try{
            userRepository.deleteById(getUserByUsername(username).getId());
            }catch(UsernameNotFoundException ex){
                throw new UserNotFoundException(username);
            }
        }

        @Override
        public List<IpmUser> getUsers() {
            return (List<IpmUser>)userRepository.findAll();
        }

        @Override
        public IpmUser getUser(Long id) {
            Optional<IpmUser> user = userRepository.findById(id);
            if(user.isPresent()){
                return user.get();
            }else{
                throw new UserNotFoundException(id);
            }
        }

        @Override
        public List<IpmUser> getUsersByGroupName(String groupName) {
            List<IpmUser> users = (List<IpmUser>)userRepository.findByGroupName(groupName);
            if(users.isEmpty()){
                throw new UserNotFoundException();
            }
                return users;
        }

        @Override
        public List<IpmUser> getUsersByGroupCode(String groupCode) {
            List<IpmUser> users = (List<IpmUser>)userRepository.findByGroupCode(groupCode);
            if(users.isEmpty()){
                throw new UserNotFoundException();
            }
                return users;
        }

        @Override
        public IpmUser saveUser(IpmUser user) {
            if(user.getRole().equals("USER")){
                user.setAuthorities(Authorities.ADMIN_AUTHORITIES);
            }else if(user.getRole().equals("ADMIN")){
                user.setAuthorities(Authorities.USER_AUTHORITIES);
            }
            return userRepository.save(user);
        }

        @Override
        public String checkUsernameAvailability(String username) {
            Optional<IpmUser> ipmUser = userRepository.findByUsername(username);
            if(ipmUser.isPresent()){
                return String.format("Username '%s' is not available.", username);
            }
            return null;
        }

        @Override
        public IpmUser getUserByUsername(String username) {
            Optional<IpmUser> ipmUser = userRepository.findByUsername(username);
            if(ipmUser.isPresent()){
                return ipmUser.get();
            }else{
                throw new UsernameNotFoundException(username);
            }
        }

        @Override
        public String generateStringForGroupCode(){
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 25;
            Random random = new Random();
    
            return random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
            
        }

}

package com.slb.sprinteye.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.slb.sprinteye.user.service.UserService;

public class RepositoryUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserDetailsService.class);

    @Autowired
    private UserService userService;
    
  

  
 
    

    /**
     * Loads the user information.
     * @param username  The username of the requested user.
     * @return  The information of the user.
     * @throws UsernameNotFoundException    Thrown if no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
       return userService.loadUserByUsername(username);
      

    }
}

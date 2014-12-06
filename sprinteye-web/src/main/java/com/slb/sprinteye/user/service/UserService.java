package com.slb.sprinteye.user.service;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.slb.sprinteye.user.model.Role;
import com.slb.sprinteye.user.model.User;
import com.slb.sprinteye.user.repository.RoleRepository;
import com.slb.sprinteye.user.repository.UserRepository;
import com.slb.sprinteye.user.view.SignupForm;


@Service
@Repository
@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class UserService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
     UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    
    @PersistenceContext
    private EntityManager manager;

   
   


   
   
    public User registerNewUserAccount(SignupForm registratioForm) throws DuplicateEmailException, PasswordIsEmptyException {
        LOGGER.debug("Registering new user account with information: {}", registratioForm);

       
        User.Builder userBilder = User.getBuilder()
                .email(registratioForm.getEmail())
                .firstName(registratioForm.getFirstName())
                .lastName(registratioForm.getLastName())
                .password(registratioForm.getPassword());

        User userAccountData = userBilder.build();


        return registerUserAccount(userAccountData);
    }

  
 
    private User registerUserAccount(User userAccountData) throws DuplicateEmailException, PasswordIsEmptyException {
        LOGGER.debug("Registering new user account with information: {}", userAccountData);
        
        if(userAccountData.getSignInProvider()==null){
        	if(StringUtils.isEmpty(userAccountData.getPassword())){
        		 throw new PasswordIsEmptyException("The password  can not be  empty.");
        	}
        	
        }
        if(userAccountData.getId()!=null){
        	if(userAccountData.getRoles().isEmpty()){
        		userAccountData.getRoles().addAll(userRepository.findOne(userAccountData.getId()).getRoles());
        	}
        }
       
        if (emailExist(userAccountData)) {
            LOGGER.debug("Email: {} exists. Throwing exception.", userAccountData.getEmail());
            throw new DuplicateEmailException("The email address: " + userAccountData.getEmail() + " is already in use.");
        }

        LOGGER.debug("Email: {} does not exist. Continuing registration.", userAccountData.getEmail());

        
        if(userAccountData.getPassword()!=null){
	        String encodedPassword = encodePassword(userAccountData);
	        userAccountData.setPassword(encodedPassword);
        }

      
        LOGGER.debug("Persisting new user with information: {}", userAccountData);

        return userRepository.saveAndFlush(userAccountData);
    }
    
    
    
  
   
   

   
    public User addRoleToUser(String nameRole,User user) throws DuplicateRoleException {
    	
    	//Set set = new HashSet<>();
    	//set.add(role);
    	if(userRepository.countByIdAndRolesLike(user.getId(),nameRole)>0){
    		throw new DuplicateRoleException("Usuário já possui esta permissão");
    	}
    	
    	Role role = new Role(nameRole);
    	role =roleRepository.saveAndFlush(role);
        user.getRoles().add(role);
        user.getRoles().get(0).getName();
    	return userRepository.saveAndFlush(user);
    	
    }
    
  
    
    private boolean emailExist(User userAccount) {
           User user = userRepository.findByEmail(userAccount.getEmail());
       
         if(userAccount.getId() == null){
		        if (user != null) {
		        
		            return true;
		        }
         }else{
        	 if(user != null && (!userAccount.getId().equals(user.getId()))){
        		 return true;
        	 }
        	 
         }


        return false;
    }

    private String encodePassword(User user) {
        String encodedPassword = null;

        if (user.isNormalRegistration()) {
            LOGGER.debug("Registration is normal registration. Encoding password.");
            encodedPassword = passwordEncoder.encode(user.getPassword());
        }

        return encodedPassword;
    }
    
   
    public  void logInUserAfterSignup(User user) {
        LOGGER.info("Logging in user: {}", user);

        UserDetails userDetails = loadUserByUsername(user.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("User: {} has been logged in.", userDetails);
    }
    

    /**
     * Loads the user information.
     * @param username  The username of the requested user.
     * @return  The information of the user.
     * @throws UsernameNotFoundException    Thrown if no user is found with the given username.
     */
   
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Loading user by username: {}", username);

        User user = userRepository.findByEmail(username);
        LOGGER.debug("Found user: {}", user);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        UserDetails principal = com.slb.sprinteye.user.security.dto.UserDetails.getBuilder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .roles(user.getRoles())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();
 
        
      
       
        LOGGER.debug("Returning user details: {}", principal);

        return principal;
    }
    
   
    public User changePassword(User user){
    	 String encodedPassword = encodePassword(user);
	      user.setPassword(encodedPassword);
	     return  userRepository.saveAndFlush(user);
	      
    }
}

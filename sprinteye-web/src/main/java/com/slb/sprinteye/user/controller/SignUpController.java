package com.slb.sprinteye.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.slb.sprinteye.user.model.User;
import com.slb.sprinteye.user.service.UserService;
import com.slb.sprinteye.user.view.SignupForm;
import com.slb.sprinteye.user.view.TestForm;


@Controller
@SessionAttributes("user")
public class SignUpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    UserService userService;
  
    
    @RequestMapping(value={"todo/signup"}, method=RequestMethod.GET)
    public ModelAndView showSignup(WebRequest request,Model model) {
	     
	      
        return new ModelAndView("todo/signup");
    }
    
    @RequestMapping(value ="todo/signup/save",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SignupForm> signup(@RequestBody @Valid SignupForm registratioForm,WebRequest request,Model model) throws Exception {
    	
    	 
    	    User registered = userService.registerNewUserAccount(registratioForm);
    	  
            userService.logInUserAfterSignup(registered);
            model.addAttribute("user", registered);
	       
	   
	        //If the user is signing in by using a social provider, this method call stores
	        //the connection to the UserConnection table. Otherwise, this method does not
	        //do anything.
	        ProviderSignInUtils.handlePostSignUp(registered.getEmail(), request);
          
           
	        return new ResponseEntity<SignupForm>(registratioForm,HttpStatus.OK);
    
    }

    @RequestMapping(value ="todo/signup/save2",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity signup2(@RequestBody  TestForm testForm,WebRequest request,Model model) throws Exception {

	        return new ResponseEntity(null,HttpStatus.OK);
    
    }

}

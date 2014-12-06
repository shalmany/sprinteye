package com.slb.sprinteye.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.slb.sprinteye.user.security.dto.UserDetails;



@Service
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	

	   @Override
	  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
	     
		   
		   super.onAuthenticationSuccess(request, response, authentication);
		   UserDetails userDetails =(UserDetails) authentication.getPrincipal();
		   
		   request.getSession(true).setAttribute("userDetails", userDetails);
		  
		  
		  
		  
	  }
}

package com.tira.restaurants.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.User;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserDetailsService userDetailsService;

	    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	    @Override
	    public String findLoggedInEmail() {
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    	if (principal instanceof UserDetails) {
	    		return ((UserDetails)principal).getUsername();
	    	} else {
	    		return principal.toString();
	    	}
	    	
	    }
	  
	    @Override
	    public void login(String email, String password) {
	        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

	        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

	        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            logger.debug(String.format("Login %s successfully!", email));
	        }
	    }
	    
		@Override
		public User findLoggedInUser() {
			// TODO Auto-generated method stub
			return null;
		}
	}


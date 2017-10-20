package com.tira.restaurants.authlisteners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.tira.restaurants.service.LoginAttemptService;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;
 
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
    	final User user = (User) e.getAuthentication().getPrincipal();
		loginAttemptService.loginFailed(user.getUsername());
    }
}


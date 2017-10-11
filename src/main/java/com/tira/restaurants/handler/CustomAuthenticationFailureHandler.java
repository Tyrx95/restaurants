package com.tira.restaurants.handler;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.tira.restaurants.service.LoginAttemptService;

@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	LoginAttemptService loginAttemptService;


    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException{
        super.onAuthenticationFailure(request, response, exception);
        
//        final String xfHeader = request.getHeader("X-Forwarded-For");
//        if (xfHeader == null) {
//        	try {
//				System.out.println("You have "+(5-loginAttemptService.getAttemptsCache().get(request.getRemoteAddr()))+" attempts.");
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			try {
//				System.out.println("You have "+(5-loginAttemptService.getAttemptsCache().get(xfHeader.split(",")[0]))+" attempts.");
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
        
        
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "invalid data");
    }
}
package com.tira.restaurants.handler;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tira.restaurants.dto.ErrorMessage;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	
	
	
	private final static Logger logger = 
			LogManager.getLogger(ExceptionHandlerAdvice.class.getName());
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity handleException(UsernameNotFoundException e) {
		logger.fatal("Username Not found exception has occured: " + e);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorMessage("Entered data is not valid"));
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity handleException(EmptyResultDataAccessException e) {
		logger.fatal("Required Entity with given id does not exist: " + e);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("");
	}
	
}

package com.tira.restaurants.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tira.restaurants.domain.User;
import com.tira.restaurants.dto.ErrorMessage;
import com.tira.restaurants.dto.UserLoginDTO;
import com.tira.restaurants.dto.UserRegisterDTO;
import com.tira.restaurants.dto.UserResponseDTO;
import com.tira.restaurants.service.ModelMapperService;
import com.tira.restaurants.service.SecurityService;
import com.tira.restaurants.service.UserService;
import com.tira.restaurants.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
    private UserService userService;
	
	@Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private ModelMapperService modelMapperService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity login(@RequestBody UserLoginDTO userDTO, HttpServletRequest request) throws UsernameNotFoundException {
    	securityService.login(userDTO.getEmail(), userDTO.getPassword());
    	User user = userService.findByEmail(userDTO.getEmail());
    	return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToUserDto(user));
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO userDTO, BindingResult bindingResult){
    	userValidator.validate(userDTO, bindingResult);
    	if(bindingResult.hasErrors()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
    	}
    	
    	User user = modelMapperService.convertToUserEntity(userDTO);
    	userService.save(user);
    	return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToUserDto(user));
    }
    
    @RequestMapping(value = "/currentUser", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getCurrentUser(HttpServletRequest request){
    	System.out.println(request.getSession());
    	User user = userService.getCurrentUser();
    	if(user != null) {
    		System.out.println("Logged in");
    		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToUserDto(user));
    		
    	}
    	System.out.println("Returning user - not logged in");
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("User does not exist."));
    	
    }
    
}

package com.tira.restaurants.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tira.restaurants.domain.Role;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	 @Autowired
     private UserRepository userRepository;
	
	 @Autowired
	 private HttpServletRequest request;
	 
	 @Autowired
	 private LoginAttemptService loginAttemptService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked(username)) {
            throw new RuntimeException("blocked");
        }
    	
    	User user = userRepository.findByEmail(username);
    	
    	if(user==null) throw new UsernameNotFoundException("invalid data");
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
    
}

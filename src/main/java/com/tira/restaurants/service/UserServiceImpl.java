package com.tira.restaurants.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Role;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.repository.RoleRepository;
import com.tira.restaurants.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SecurityService securityService;
    
    
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER");
        if(userRole==null) {
        	roleRepository.save(new Role("USER"));
        }
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
    
    @Override
    public void saveAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER");
        if(userRole==null) {
        	roleRepository.save(new Role("USER"));
        }
        Role adminRole = roleRepository.findByName("ADMIN");
        if(adminRole==null) {
        	roleRepository.save(new Role("ADMIN"));
        }
        roles.add(userRole);
        roles.add(adminRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	@Override
	public User getCurrentUser() {
		return userRepository.findByEmail(securityService.findLoggedInEmail());
	}

	@Override
	public long getUserCount() {
		return userRepository.count();
	}
    
    
    
    
}
    


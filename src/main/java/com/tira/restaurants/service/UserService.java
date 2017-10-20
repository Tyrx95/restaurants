package com.tira.restaurants.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tira.restaurants.domain.User;
import com.tira.restaurants.dto.UserEditDTO;

public interface UserService {
	void save(User user);
	void saveAdmin(User user);
	User findByEmail(String email);
	User getCurrentUser();
	long getUserCount();
	Page<User> getByFilter(String searchText, Pageable pageable);
	User editUser(UserEditDTO userDTO);
	void deleteUser(Long id);
	User getUser(Long id);
	
	
}

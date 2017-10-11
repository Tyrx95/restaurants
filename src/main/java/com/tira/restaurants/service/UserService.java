package com.tira.restaurants.service;

import com.tira.restaurants.domain.User;

public interface UserService {
	void save(User user);
	void saveAdmin(User user);
	User findByEmail(String email);
	User getCurrentUser();
	long getUserCount();
}

package com.tira.restaurants.service;

import com.tira.restaurants.domain.User;

public interface SecurityService {
	String findLoggedInEmail();
	User findLoggedInUser();
	void login(String email, String password);
}

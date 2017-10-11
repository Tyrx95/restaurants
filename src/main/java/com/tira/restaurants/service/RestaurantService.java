package com.tira.restaurants.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tira.restaurants.domain.Restaurant;

public interface RestaurantService {
	Page<Restaurant> getByFilter(String searchText, Pageable pageable);
	Restaurant getOne(Long id);
	long getRestaurantsCount();
	
}

package com.tira.restaurants.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.dto.RestaurantEditDTO;

public interface RestaurantService {
	Page<Restaurant> getByFilter(String searchText, Pageable pageable);
	Restaurant getOne(Long id);
	long getRestaurantsCount();
	void addRestaurant(Restaurant restaurant);
	boolean editRestaurant(RestaurantEditDTO editRestaurantDTO);
	void deleteRestaurant(Long id);
	Set<Category> getRestaurantCategories(Long id);
	
}

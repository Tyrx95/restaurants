package com.tira.restaurants.service;

import java.util.List;

import com.tira.restaurants.domain.Meal;

public interface MealService {
	List<Meal> getRestaurantMenu(Long restaurantId, String type);
}

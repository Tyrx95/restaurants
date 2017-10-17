package com.tira.restaurants.service;

import java.util.List;

import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.dto.MealResponseDTO;

public interface MealService {
	List<Meal> getRestaurantMenu(Long restaurantId, String type);

	void addMeals(List<MealResponseDTO> addMealsDTO);
	void editMeals(List<MealResponseDTO> editMealsDTO);
	void deleteMeals(List<MealResponseDTO> deleteMealsDTO);
}

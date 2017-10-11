package com.tira.restaurants.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.repository.MealRepository;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired
	MealRepository repository;

	@Override
	public List<Meal> getRestaurantMenu(Long restaurantId, String type) {
		return repository.findMenu(restaurantId,type);
		
	} 
	
	
}

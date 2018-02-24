package com.tira.restaurants.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.repository.MealRepository;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired
	MealRepository repository;
	
	@Autowired
	RestaurantService restaurantService;

	@Override
	public List<Meal> getRestaurantMenu(Long restaurantId, String type) {
		return repository.findMenu(restaurantId,type);
		
	}

	@Override
	public void addMeals(List<MealResponseDTO> addMealsDTO) {
		for(MealResponseDTO mealDTO : addMealsDTO) {
			Meal meal = new Meal(restaurantService.getOne(mealDTO.getIdRestaurant()), mealDTO.getType(), mealDTO.getName(),
					mealDTO.getPrice(), mealDTO.getDescription());
			repository.save(meal);
		}
	}

	@Override
	public void editMeals(List<MealResponseDTO> editMealsDTO) {
		for(MealResponseDTO mealDTO : editMealsDTO) {
			Meal meal = repository.findOne(mealDTO.getId());
			meal.setDescription(mealDTO.getDescription());
			meal.setName(mealDTO.getName());
			meal.setPrice(mealDTO.getPrice());
			meal.setType(mealDTO.getType());
			repository.save(meal);
		}
	}

	@Override
	public void deleteMeals(List<MealResponseDTO> deleteMealsDTO) {
		for(MealResponseDTO mealDTO : deleteMealsDTO) {
			repository.delete(mealDTO.getId());
		}
		
	}
	

}

package com.tira.restaurants.service;

import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.dto.CategoryDTO;
import com.tira.restaurants.dto.LocationFilterResponseDTO;
import com.tira.restaurants.dto.LocationResponseDTO;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.dto.UserRegisterDTO;
import com.tira.restaurants.dto.UserResponseDTO;

public interface ModelMapperService {
	RestaurantResponseDTO convertToRestaurantDto(Restaurant restaurant);
	MealResponseDTO convertToMealDto(Meal meal);
	CategoryDTO convertToCategoryDto(Category category);
	LocationResponseDTO convertToLocationDto(Location location);
	UserResponseDTO convertToDto(User user);
	User convertToEntity(UserRegisterDTO userDto);
	LocationFilterResponseDTO convertToLocationFilterResponseDto(Location location);
}

package com.tira.restaurants.service;

import java.util.LinkedHashMap;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Comment;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.domain.Table;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.dto.CategoryDTO;
import com.tira.restaurants.dto.CommentDTO;
import com.tira.restaurants.dto.LocationFilterResponseDTO;
import com.tira.restaurants.dto.LocationResponseDTO;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.dto.RestaurantRequestDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.dto.SuccessfulReservationDTO;
import com.tira.restaurants.dto.TableDTO;
import com.tira.restaurants.dto.UserRegisterDTO;
import com.tira.restaurants.dto.UserResponseDTO;

public interface ModelMapperService {
	RestaurantResponseDTO convertToRestaurantDto(Restaurant restaurant);
	MealResponseDTO convertToMealDto(Meal meal);
	CategoryDTO convertToCategoryDto(Category category);
	LocationResponseDTO convertToLocationDto(Location location);
	UserResponseDTO convertToUserDto(User user);
	User convertToUserEntity(UserRegisterDTO userDto);
	LocationFilterResponseDTO convertToLocationFilterResponseDto(Location location);
	Restaurant convertToRestaurantEntity(RestaurantRequestDTO restaurantDTO);
	CommentDTO convertToCommentDTO(Comment comment);
	TableDTO convertToTableDTO(Table table);
	TableDTO convertFromHashMapToTablesDTO(LinkedHashMap lhm);
	MealResponseDTO convertFromHashMapToMealsDTO(LinkedHashMap lhm);
	SuccessfulReservationDTO convertToResponseSuccessfulReservationDTO(Reservation reservation);
	
}

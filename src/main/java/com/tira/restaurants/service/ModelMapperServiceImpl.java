package com.tira.restaurants.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class ModelMapperServiceImpl implements ModelMapperService {
	
	@Autowired
	ModelMapper modelMapper;
	
	
	public RestaurantResponseDTO convertToRestaurantDto(Restaurant restaurant) {
		PropertyMap<Restaurant,RestaurantResponseDTO> model = new PropertyMap<Restaurant,RestaurantResponseDTO>() {
			@Override
			protected void configure(){
			map().setLocation(source.getLocation().getId());
			}
			};
		TypeMap<Restaurant, RestaurantResponseDTO> typeMap = modelMapper.getTypeMap(Restaurant.class, RestaurantResponseDTO.class);
		if (typeMap == null) {
			   modelMapper.addMappings(model);
			}
    	RestaurantResponseDTO restaurantDto = modelMapper.map(restaurant, RestaurantResponseDTO.class);
        return restaurantDto;
    }
	
	public MealResponseDTO convertToMealDto(Meal meal) {
		PropertyMap<Meal,MealResponseDTO> model = new PropertyMap<Meal,MealResponseDTO>() {
			@Override
			protected void configure(){
			map().setRestaurant(source.getRestaurant().getId());
			}
			};
		TypeMap<Meal, MealResponseDTO> typeMap = modelMapper.getTypeMap(Meal.class, MealResponseDTO.class);
		if (typeMap == null) {
			   modelMapper.addMappings(model);
			}
		MealResponseDTO mealDto = modelMapper.map(meal, MealResponseDTO.class);
        return mealDto;
    }
	
	public CategoryDTO convertToCategoryDto(Category category) {
		CategoryDTO categoryDto = modelMapper.map(category, CategoryDTO.class);
        return categoryDto;
    }
	
	
	public LocationResponseDTO convertToLocationDto(Location location) {
		LocationResponseDTO locationDto = modelMapper.map(location, LocationResponseDTO.class);
        return locationDto;
    }
	
	
	public UserResponseDTO convertToDto(User user) {
    	UserResponseDTO userDto = modelMapper.map(user, UserResponseDTO.class);
        return userDto;
    }
    
	public User convertToEntity(UserRegisterDTO userDto) {
    	User user = modelMapper.map(userDto, User.class);
        return user;
    }

	@Override
	public LocationFilterResponseDTO convertToLocationFilterResponseDto(Location location) {
		LocationFilterResponseDTO locationDTO = modelMapper.map(location, LocationFilterResponseDTO.class);
		return locationDTO;
	}
    
	
}

package com.tira.restaurants.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Comment;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.dto.CategoryDTO;
import com.tira.restaurants.dto.CommentDTO;
import com.tira.restaurants.dto.LocationFilterResponseDTO;
import com.tira.restaurants.dto.LocationResponseDTO;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.dto.RestaurantEditDTO;
import com.tira.restaurants.dto.RestaurantRequestDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.dto.UserRegisterDTO;
import com.tira.restaurants.dto.UserResponseDTO;

@Service
public class ModelMapperServiceImpl implements ModelMapperService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	LocationService locationService;

	@Autowired
	CategoryService categoryService;

	public RestaurantResponseDTO convertToRestaurantDto(Restaurant restaurant) {
		PropertyMap<Restaurant, RestaurantResponseDTO> model = new PropertyMap<Restaurant, RestaurantResponseDTO>() {
			@Override
			protected void configure() {
				map().setLocation(source.getLocation().getId());
			}
		};
		TypeMap<Restaurant, RestaurantResponseDTO> typeMap = modelMapper.getTypeMap(Restaurant.class,
				RestaurantResponseDTO.class);
		if (typeMap == null) {
			modelMapper.addMappings(model);
		}
		RestaurantResponseDTO restaurantDto = modelMapper.map(restaurant, RestaurantResponseDTO.class);
		return restaurantDto;
	}

	public Restaurant convertToRestaurantEntity(RestaurantRequestDTO restaurantDTO) {

		Converter<RestaurantRequestDTO, Restaurant> converter = new AbstractConverter<RestaurantRequestDTO, Restaurant>() {
			Restaurant restaurant = new Restaurant();

			@Override
			protected Restaurant convert(RestaurantRequestDTO source) {
				Location location = locationService.getLocation(source.getLocation());
				restaurant.setId(null);
				restaurant.setLocation(location);
				Set<Category> categories = categoryService.getCategories(source.getCategories());
				restaurant.setCategories(categories);
				restaurant.setLongitude(source.getLongitude());
				restaurant.setLatitude(source.getLatitude());
				restaurant.setRestaurantName(source.getRestaurantName());
				restaurant.setPriceRange(source.getPriceRange());
				restaurant.setDescription(source.getDescription());
				restaurant.setImageFileName(source.getImageFileName());
				restaurant.setCoverFileName(source.getCoverFileName());
				restaurant.setMark(0);
				restaurant.setVotes(0);
				return restaurant;
			}
		};
		TypeMap<RestaurantRequestDTO, Restaurant> typeMap = modelMapper.getTypeMap(RestaurantRequestDTO.class,
				Restaurant.class);
		if (typeMap == null) {
			modelMapper.createTypeMap(RestaurantRequestDTO.class, Restaurant.class).setConverter(converter);
		}
		Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
		return restaurant;

	}


	public MealResponseDTO convertToMealDto(Meal meal) {
		PropertyMap<Meal, MealResponseDTO> model = new PropertyMap<Meal, MealResponseDTO>() {
			@Override
			protected void configure() {
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

	@Override
	public CommentDTO convertToCommentDTO(Comment comment) {
		Converter<Comment, CommentDTO> converter = new AbstractConverter<Comment, CommentDTO>() {
			CommentDTO commentDTO = new CommentDTO();

			@Override
			protected CommentDTO convert(Comment source) {
				commentDTO.setMark(source.getMark());
				commentDTO.setName(source.getUser().getFirstName()+" "+source.getUser().getLastName());
				commentDTO.setInsertTime(source.getInsertTime().toString());
				commentDTO.setComment(source.getComment());
				return commentDTO;
			}
		};
		
		TypeMap<Comment, CommentDTO> typeMap = modelMapper.getTypeMap(Comment.class,
				CommentDTO.class);
		if (typeMap == null) {
			modelMapper.createTypeMap(Comment.class, CommentDTO.class).setConverter(converter);
		}
		CommentDTO commentDTO= modelMapper.map(comment, CommentDTO.class);
		return commentDTO;
	}

	

}

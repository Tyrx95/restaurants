package com.tira.restaurants.service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
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
import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.domain.Table;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.dto.CategoryDTO;
import com.tira.restaurants.dto.CommentDTO;
import com.tira.restaurants.dto.LocationFilterResponseDTO;
import com.tira.restaurants.dto.LocationResponseDTO;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.dto.RestaurantEditDTO;
import com.tira.restaurants.dto.RestaurantRequestDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.dto.SuccessfulReservationDTO;
import com.tira.restaurants.dto.TableDTO;
import com.tira.restaurants.dto.UserEditDTO;
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

	public UserResponseDTO convertToUserDto(User user) {
		UserResponseDTO userDto = modelMapper.map(user, UserResponseDTO.class);
		return userDto;
	}

	public User convertToUserEntity(UserRegisterDTO userDto) {
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
				commentDTO.setName(source.getUser().getFirstName() + " " + source.getUser().getLastName());
				commentDTO.setInsertTime(source.getInsertTime().toString());
				commentDTO.setComment(source.getComment());
				return commentDTO;
			}
		};

		TypeMap<Comment, CommentDTO> typeMap = modelMapper.getTypeMap(Comment.class, CommentDTO.class);
		if (typeMap == null) {
			modelMapper.createTypeMap(Comment.class, CommentDTO.class).setConverter(converter);
		}
		CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
		return commentDTO;
	}

	@Override
	public TableDTO convertToTableDTO(Table table) {
		PropertyMap<Table, TableDTO> model = new PropertyMap<Table, TableDTO>() {
			@Override
			protected void configure() {
				map().setIdRestaurant(source.getRestaurant().getId());
			}
		};
		TypeMap<Table, TableDTO> typeMap = modelMapper.getTypeMap(Table.class, TableDTO.class);
		if (typeMap == null) {
			modelMapper.addMappings(model);
		}
		TableDTO tableDto = modelMapper.map(table, TableDTO.class);
		return tableDto;

	}

	@Override
	public TableDTO convertFromHashMapToTablesDTO(LinkedHashMap lhm) {
		Converter<LinkedHashMap, TableDTO> converter = new AbstractConverter<LinkedHashMap, TableDTO>() {
			

			@Override
			protected TableDTO convert(LinkedHashMap source) {
				TableDTO tableDTO = new TableDTO();
				if (source.get("id") != null) {
					tableDTO.setId(Long.parseLong((String) source.get("id")));
				}

				if (source.get("idRestaurant") != null) {
					tableDTO.setIdRestaurant(Long.parseLong((String) source.get("idRestaurant")));
				}

				if (source.get("sittingPlaces") != null) {
					tableDTO.setSittingPlaces((Integer) source.get("sittingPlaces"));
				}

				return tableDTO;
			}
		};

		TypeMap<LinkedHashMap, TableDTO> typeMap = modelMapper.getTypeMap(LinkedHashMap.class, TableDTO.class);
		if (typeMap == null) {
			modelMapper.createTypeMap(LinkedHashMap.class, TableDTO.class).setConverter(converter);
		}
		TableDTO tableDTO = modelMapper.map(lhm, TableDTO.class);
		return tableDTO;
	}

	@Override
	public MealResponseDTO convertFromHashMapToMealsDTO(LinkedHashMap lhm) {
		Converter<LinkedHashMap, MealResponseDTO> converter = new AbstractConverter<LinkedHashMap, MealResponseDTO>() {
			@Override
			protected MealResponseDTO convert(LinkedHashMap source) {
				MealResponseDTO mealDTO = new MealResponseDTO();
				if (source.get("id") != null) {
					mealDTO.setId(Long.parseLong((String) source.get("id")));
				}

				if (source.get("idRestaurant") != null) {
					mealDTO.setRestaurant(Long.parseLong((String) source.get("idRestaurant")));
				}
				mealDTO.setName((String) source.get("name"));
				mealDTO.setDescription((String) source.get("description"));
				if (source.get("price") != null) {
					mealDTO.setPrice(Double.parseDouble((String) source.get("price")));
				}
				mealDTO.setType((String) source.get("type"));
				return mealDTO;
			}
		};

		TypeMap<LinkedHashMap, MealResponseDTO> typeMap = modelMapper.getTypeMap(LinkedHashMap.class,
				MealResponseDTO.class);
		if (typeMap == null) {
			modelMapper.createTypeMap(LinkedHashMap.class, MealResponseDTO.class).setConverter(converter);
		}
		MealResponseDTO mealDTO = modelMapper.map(lhm, MealResponseDTO.class);
		return mealDTO;
	}

	@Override
	public SuccessfulReservationDTO convertToResponseSuccessfulReservationDTO(Reservation reservation) {
		Converter<Reservation, SuccessfulReservationDTO> converter = new AbstractConverter<Reservation, SuccessfulReservationDTO>() {
			@Override
			protected SuccessfulReservationDTO convert(Reservation source) {
				SuccessfulReservationDTO reservationDTO = new SuccessfulReservationDTO();
				reservationDTO.setId(source.getId());
				reservationDTO.setIdTable(source.getTable().getId());
				reservationDTO.setIdUser(source.getUser().getId());
				reservationDTO.setPersons(source.getPersons());
				reservationDTO.setReservationDateTime(LocalDateTime.of(source.getReservationDate(), source.getReservationTime()));
				return reservationDTO;
			}
		};

		TypeMap<Reservation, SuccessfulReservationDTO> typeMap = modelMapper.getTypeMap(Reservation.class,
				SuccessfulReservationDTO.class);
		if (typeMap == null) {
			modelMapper.createTypeMap(Reservation.class, SuccessfulReservationDTO.class).setConverter(converter);
		}
		SuccessfulReservationDTO reservationDTO = modelMapper.map(reservation, SuccessfulReservationDTO.class);
		return reservationDTO;
	}

}

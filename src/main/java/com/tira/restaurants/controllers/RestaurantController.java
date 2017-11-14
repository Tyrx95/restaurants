package com.tira.restaurants.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.dto.CategoryDTO;
import com.tira.restaurants.dto.ErrorMessage;
import com.tira.restaurants.dto.FilterDTO;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.service.CategoryService;
import com.tira.restaurants.service.CommentService;
import com.tira.restaurants.service.MealService;
import com.tira.restaurants.service.ModelMapperService;
import com.tira.restaurants.service.ReservationService;
import com.tira.restaurants.service.RestaurantService;

@Controller
public class RestaurantController {
	
	@Autowired 
	RestaurantService restaurantService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired 
	MealService mealService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
    private ModelMapperService modelMapperService;
	
	@RequestMapping(value = "/getRestaurantsByFilter", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getRestaurantsByFilter(@RequestBody FilterDTO filter)  {
		System.out.println(filter);
		
		Page<Restaurant> restaurantPages = restaurantService.getByFilter(filter);
		List<RestaurantResponseDTO> restaurantsDTO = new ArrayList<>();
		
		for(Restaurant restaurant : restaurantPages.getContent()) {
			restaurantsDTO.add(modelMapperService.convertToRestaurantDto(restaurant));
		}
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("restaurants", restaurantsDTO);
		responseBody.put("numberOfRestaurantPages", restaurantPages.getTotalPages());
    	return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
	
	@RequestMapping(value = "/allRestaurantsSortReservationsToday", method = RequestMethod.GET,produces="application/json")
    public ResponseEntity allRestaurantsSortReservationsToday()  {
		Set<Restaurant> restaurants = reservationService.getRestaurantsSortReservationsToday();
		List<RestaurantResponseDTO> restaurantsDTO = new ArrayList<>();
		
		for(Restaurant restaurant: restaurants) {
			restaurantsDTO.add(modelMapperService.convertToRestaurantDto(restaurant));
		}
		return ResponseEntity.status(HttpStatus.OK).body(restaurantsDTO);
		
    }
	
	
	@RequestMapping(value = "/getRestaurantDetails", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getRestaurantDetails(@RequestBody Map<String, Object> body)  {
		
		Restaurant restaurant = restaurantService.getOne(Long.parseLong((String) body.get("Id")));
		if(restaurant!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToRestaurantDto(restaurant));
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Restaurant doesn't exist"));
		
    }
	
	@RequestMapping(value = "/getRestaurantMenu", method = RequestMethod.POST, consumes="application/json" ,produces="application/json")
    public ResponseEntity getRestaurantMenu(@RequestBody Map<String, Object> body)  {
		List<Meal> menu = mealService.getRestaurantMenu(Long.parseLong((String) body.get("idRestaurant")), (String) body.get("type"));
		List<MealResponseDTO> menuDTO = new ArrayList<>();
		for(Meal meal : menu) {
			menuDTO.add(modelMapperService.convertToMealDto(meal));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(menuDTO);
		
    }
	
	@RequestMapping(value = "/insertComment", method = RequestMethod.POST, consumes="application/json" ,produces="application/json")
    public ResponseEntity insertComment(@RequestBody Map<String, Object> body)  {
		commentService.insertComment((Integer) body.get("mark"), (Integer) body.get("idUser") , (Integer) body.get("idRestaurant") , (String) body.get("comment"));
		return ResponseEntity.status(HttpStatus.OK).body("{}");
		
    }
	
	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getAllCategories()  {
		List<Category> categories = categoryService.getAll();
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		for(Category category : categories) {
			categoriesDTO.add(modelMapperService.convertToCategoryDto(category));
		}
		return ResponseEntity.status(HttpStatus.OK).body(categoriesDTO);
		
    }

	
}

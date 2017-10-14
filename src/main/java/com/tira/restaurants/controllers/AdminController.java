package com.tira.restaurants.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.dto.CategoryDTO;
import com.tira.restaurants.dto.ErrorMessage;
import com.tira.restaurants.dto.FilterDTO;
import com.tira.restaurants.dto.LocationFilterResponseDTO;
import com.tira.restaurants.dto.LocationResponseDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.service.CategoryService;
import com.tira.restaurants.service.LocationService;
import com.tira.restaurants.service.ModelMapperService;
import com.tira.restaurants.service.RestaurantService;
import com.tira.restaurants.service.UserService;

@Controller
@RequestMapping(value="/admin")
@SuppressWarnings("rawtypes")
public class AdminController {
	
	@Autowired 
	RestaurantService restaurantService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired 
	UserService userService;	
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	
	
	@RequestMapping(value = "/getFilteredLocations", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getFilteredLocations(@RequestBody FilterDTO filter)  {
		PageRequest pageReq = new PageRequest(filter.getPageNumber()-1, filter.getItemsPerPage());
		String searchText = filter.getSearchText();
		Page<Location> locationPages = locationService.getByFilter(searchText,pageReq);
		List<LocationFilterResponseDTO> locationsDTO = new ArrayList<>();
		
		for(Location location : locationPages.getContent()) {
			locationsDTO.add(modelMapperService.convertToLocationFilterResponseDto(location));
		}
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("locations", locationsDTO);
		responseBody.put("numberOfPages", locationPages.getTotalPages());
    	return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	
    }
	
	@RequestMapping(value = "/getAdministrationCounters", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getAdministrationCounters()  {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("restaurantsNumber", restaurantService.getRestaurantsCount());
		responseBody.put("locationsNumber", locationService.getLocationCount());
		responseBody.put("usersNumber", userService.getUserCount());
		
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
	
	@RequestMapping(value = "/addLocation", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity addLocation(@RequestBody Map<String, Object> requestBody)  {
		Location location = new Location((String) requestBody.get("name"),0);
		locationService.addLocation(location);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToLocationFilterResponseDto(location));
    }
	
	@RequestMapping(value = "/editLocation", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity editLocation(@RequestBody LocationFilterResponseDTO newLocation)  {
		Location location = locationService.editLocation(newLocation.getId(),newLocation.getName());
		if(location == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Location doesn't exist!"));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToLocationFilterResponseDto(location));
		}
    }
	
	@RequestMapping(value = "/deleteLocation", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity deleteLocation(@RequestBody Map<String, Object> requestBody)  {
		locationService.deleteLocation(new Long((Integer) requestBody.get("id")));
		return ResponseEntity.status(HttpStatus.OK).body("");
    }
	
	@RequestMapping(value = "/getLocationDetails", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getLocationDetails(@RequestBody Map<String, Object> requestBody)  {
		Location location = locationService.getLocation(new Long((Integer) requestBody.get("id")));
		if(location == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToLocationFilterResponseDto(location));
    }
	
	
	@RequestMapping(value = "/getFilteredCategories", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getFilteredCategories(@RequestBody FilterDTO filter)  {
		PageRequest pageReq = new PageRequest(filter.getPageNumber()-1, filter.getItemsPerPage());
		String searchText = filter.getSearchText();
		Page<Category> categoryPages = categoryService.getByFilter(searchText,pageReq);
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		
		for(Category category : categoryPages.getContent()) {
			categoriesDTO.add(modelMapperService.convertToCategoryDto(category));
		}
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("categories", categoriesDTO);
		responseBody.put("numberOfPages", categoryPages.getTotalPages());
    	return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	
    }
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity addCategory(@RequestBody Map<String, Object> requestBody)  {
		Category category = new Category((String) requestBody.get("name"));
		categoryService.addCategory(category);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToCategoryDto(category));
	
    }
	
	@RequestMapping(value = "/editCategory", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity editCategory(@RequestBody CategoryDTO editedCategory)  {
		Category category = categoryService.editCategory(editedCategory.getId(),editedCategory.getName());
		if(category == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Category doesn't exist!"));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToCategoryDto(category));
		}
    }
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity deleteCategory(@RequestBody Map<String, Object> requestBody)  {
		categoryService.deleteCategory(new Long((Integer) requestBody.get("id")));
		return ResponseEntity.status(HttpStatus.OK).body("");
    }
	
	@RequestMapping(value = "/getCategoryDetails", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getCategoryDetails(@RequestBody Map<String, Object> requestBody)  {
		Category category = categoryService.getCategory(new Long((Integer) requestBody.get("id")));
		if(category == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToCategoryDto(category));
    }
	
}

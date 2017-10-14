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
import com.tira.restaurants.dto.LocationFilterResponseDTO;
import com.tira.restaurants.dto.LocationResponseDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.service.LocationService;
import com.tira.restaurants.service.ModelMapperService;
import com.tira.restaurants.service.RestaurantService;
import com.tira.restaurants.service.UserService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired 
	RestaurantService restaurantService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired 
	UserService userService;	
	
	@Autowired
	ModelMapperService modelMapperService;
	
	
	@RequestMapping(value = "/getFilteredLocations", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getFilteredLocations(@RequestBody Map<String, Object> filter)  {
		PageRequest pageReq = new PageRequest((int)filter.get("pageNumber") - 1, (int)filter.get("itemsPerPage"));
		String searchText = (String) filter.get("searchText");
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
}

package com.tira.restaurants.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.dto.ErrorMessage;
import com.tira.restaurants.dto.LocationResponseDTO;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.service.LocationService;
import com.tira.restaurants.service.ModelMapperService;

@Controller
public class LocationController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	
	@RequestMapping(value = "/getRestaurantsLocations", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getRestaurantDetails()  {
		List<Location> locations = locationService.getAll();
		List<LocationResponseDTO> locationsDTO = new ArrayList<>();
		for(Location location: locations) {
			locationsDTO.add(modelMapperService.convertToLocationDto(location));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(locationsDTO);
		
    }
	
}

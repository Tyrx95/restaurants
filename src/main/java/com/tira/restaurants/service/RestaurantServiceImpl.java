package com.tira.restaurants.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.dto.RestaurantEditDTO;
import com.tira.restaurants.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository repository; 
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	public Page<Restaurant> getByFilter(String searchText, Pageable pageable) {
		
		if(searchText!=null) {
			return repository.getRestaurantsByFilter(searchText,pageable);
		}
		else return repository.findAll(pageable);
		
	}

	@Override
	public Restaurant getOne(Long id) {
			return repository.findOne(id);
	}

	@Override
	public long getRestaurantsCount() {
		return repository.count();
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		System.out.println(restaurant);
		repository.save(restaurant);
		System.out.println("saved");
		
	}

	@Override
	public boolean editRestaurant(RestaurantEditDTO editRestaurantDTO) {
		
		Restaurant restaurant = repository.findOne(editRestaurantDTO.getId());
		if(restaurant==null) {
			return false;
		}
		editRestaurantValues(restaurant, editRestaurantDTO);
		repository.save(restaurant);
		return true;
	}
	
	@Override
	public void deleteRestaurant(Long id) {
		repository.delete(id);
	}
	
	@Override
	public Set<Category> getRestaurantCategories(Long id) {
		Restaurant restaurant = getOne(id);
		if(restaurant!=null) {
			return restaurant.getCategories();
		}
		return null;
	}
	
	

	private void editRestaurantValues(Restaurant restaurant, RestaurantEditDTO editRestaurantDTO) {
		Location location = locationService.getLocation(editRestaurantDTO.getLocation());
		restaurant.setLocation(location);
		Set<Category> categories = categoryService.getCategories(editRestaurantDTO.getCategories());
		restaurant.setCategories(categories);
		restaurant.setLongitude(editRestaurantDTO.getLongitude());
		restaurant.setLatitude(editRestaurantDTO.getLatitude());
		restaurant.setRestaurantName(editRestaurantDTO.getRestaurantName());
		restaurant.setPriceRange(editRestaurantDTO.getPriceRange());
		restaurant.setDescription(editRestaurantDTO.getDescription());
		restaurant.setImageFileName(editRestaurantDTO.getImageFileName());
		restaurant.setCoverFileName(editRestaurantDTO.getCoverFileName());
		restaurant.setMark(editRestaurantDTO.getMark());
		restaurant.setVotes(editRestaurantDTO.getVotes());
		restaurant.setFoodType(editRestaurantDTO.getFoodType());
	}

	
}

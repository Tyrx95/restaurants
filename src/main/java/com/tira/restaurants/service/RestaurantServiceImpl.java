package com.tira.restaurants.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.dto.FilterDTO;
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
	public Page<Restaurant> getByFilter(FilterDTO filter) {
		PageRequest pageReq = new PageRequest(filter.getPageNumber()-1, filter.getItemsPerPage());
		String searchText = filter.getSearchText();
		Integer rating = filter.getRating();
		Integer priceRange = filter.getPriceRange();
		List<String> categories = filter.getCategories();
		String foodType="";
		if(categories!=null && !categories.isEmpty()) {
			foodType+=categories.remove(0);
			for(String category: categories) {
				foodType+=category;
				foodType+=" | ";
			}
		}
		
		return repository.getRestaurantsByFilter(searchText,foodType,priceRange, rating, pageReq);
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
		if (editRestaurantDTO.getLocation() != null) {
			Location location = locationService.getLocation(editRestaurantDTO.getLocation());
			restaurant.setLocation(location);
		}
		if (editRestaurantDTO.getCategories() != null) {
			Set<Category> categories = categoryService.getCategories(editRestaurantDTO.getCategories());
			restaurant.setCategories(categories);
		}
		if (editRestaurantDTO.getLongitude() != null) {
			restaurant.setLongitude(editRestaurantDTO.getLongitude());
		}
		if (editRestaurantDTO.getLatitude() != null) {
			restaurant.setLatitude(editRestaurantDTO.getLatitude());
		}
		if (editRestaurantDTO.getRestaurantName() != null) {
			restaurant.setRestaurantName(editRestaurantDTO.getRestaurantName());
		}
		if (editRestaurantDTO.getPriceRange() != null) {
			restaurant.setPriceRange(editRestaurantDTO.getPriceRange());
		}
		if (editRestaurantDTO.getDescription() != null) {
			restaurant.setDescription(editRestaurantDTO.getDescription());
		}
		if(editRestaurantDTO.getImageFileName() != null) {
			restaurant.setImageFileName(editRestaurantDTO.getImageFileName());
		}
		if (editRestaurantDTO.getCoverFileName() != null) {
			restaurant.setCoverFileName(editRestaurantDTO.getCoverFileName());
		}
		if (editRestaurantDTO.getMark() != null) {
			restaurant.setMark(editRestaurantDTO.getMark());
		}
		if (editRestaurantDTO.getVotes() != null) {
			restaurant.setVotes(editRestaurantDTO.getVotes());
		}
		if (editRestaurantDTO.getFoodType() != null) {
			restaurant.setFoodType(editRestaurantDTO.getFoodType());
		}
	}
	
}

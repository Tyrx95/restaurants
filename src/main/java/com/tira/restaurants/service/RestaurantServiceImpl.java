package com.tira.restaurants.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository repository; 
	
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
	
	

}

package com.tira.restaurants.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tira.restaurants.domain.Location;
import com.tira.restaurants.dto.LocationFilterResponseDTO;

public interface LocationService {
	
	List<Location> getAll();
	long getLocationCount();
	Page<Location> getByFilter(String searchText, Pageable pageable);
	void addLocation(Location location);
	Location editLocation(Long id, String name);
	void deleteLocation(Long id);
	Location getLocation(Long id);
}

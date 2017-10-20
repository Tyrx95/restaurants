package com.tira.restaurants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Location;
import com.tira.restaurants.repository.LocationRepository;


@Service
public class LocationServiceImpl implements LocationService {

	@Autowired 
	LocationRepository locationRepository;

	@Override
	public List<Location> getAll() {
		return locationRepository.findAll();
		
	}

	@Override
	public long getLocationCount() {
		return locationRepository.count();
	}

	@Override
	public Page<Location> getByFilter(String searchText, Pageable pageable) {
		
		if(searchText!=null) {
			return locationRepository.getLocationsByFilter(searchText,pageable);
		}
		else return locationRepository.findAll(pageable);
	}

	@Override
	public void addLocation(Location location) {
		locationRepository.save(location);
	}

	@Override
	public Location editLocation(Long id, String name) {
		Location location = locationRepository.findOne(id);
		if(location == null) {
			return null;
		}
		location.setName(name);
		addLocation(location);
		return location;
	}

	@Override
	public void deleteLocation(Long id) {
		locationRepository.delete(id);
		
	}

	@Override
	public Location getLocation(Long id) {
		return locationRepository.findOne(id);
	}
	
	
	
	
}

package com.tira.restaurants.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.domain.Table;
import com.tira.restaurants.repository.ReservationRepository;
import com.tira.restaurants.repository.RestaurantRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	UserService userService;

	@Override
	public Reservation makeReservation(Integer persons, LocalDate reservationDate, LocalTime reservationHour,
			Long idRestaurant, Long idUser) {

		Restaurant restaurant = restaurantRepository.findOne(idRestaurant);
		//TODO Handle null pointer exception
		Set<Table> tables = restaurant.getTables();
		Table bestTable = null;
		int bestSize = Integer.MAX_VALUE;
		for (Table table : tables) {
			//TODO 
			if (table.getSittingPlaces() >= persons && table.getSittingPlaces() < bestSize && 
					reservationRepository.getReservationByTable(idRestaurant, reservationDate, 
							reservationHour, table.getId()) == null) {
				bestTable = table;
				bestSize = table.getSittingPlaces();
			}
		}

		if(bestTable == null)
			return null;
		Reservation reservation = new Reservation(persons, bestTable, userService.getUser(idUser), reservationDate, 
										reservationHour, restaurantRepository.findOne(idRestaurant));
		reservationRepository.save(reservation);
		return reservation;
		
	}

	@Override
	public Set<Restaurant> getRestaurantsSortReservationsToday() {
		LocalDate date = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		Set<Reservation> reservations = reservationRepository.getRestaurantsSortReservationsToday(date);
		Set<Restaurant> restaurants = new HashSet<>();
		for(Reservation reservation : reservations) {
			restaurants.add(reservation.getRestaurant());
		}
		return restaurants;
		
	}

	@Override
	public Map<String, Object> checkReservationAvailability(Integer persons, LocalDate date, LocalTime time,
			Long idRestaurant) {
		Map<String,Object> response = new HashMap<>();
		if(checkAvailabilityForATable(idRestaurant, persons, date, time)) 
		{
			response.put("isAvailable", true);
			return response;
		}
		
		response.put("isAvailable", false);
		List<String> bestTime = new ArrayList<>();
		for(int diff=30;diff<=120;diff+=30) {
			if(checkAvailabilityForATable(idRestaurant, persons,date,time.plusMinutes(diff))) {
				String timeString = time.plusMinutes(diff).format(DateTimeFormatter.ofPattern("hh:mm a"));
				bestTime.add(timeString);
			}
		}
		
		for(int diff=30;diff<=120;diff+=30) {
			if(checkAvailabilityForATable(idRestaurant, persons,date,time.minusMinutes(diff))) {
				String timeString = time.minusMinutes(diff).format(DateTimeFormatter.ofPattern("hh:mm a"));
				bestTime.add(timeString);
			}
		}
		if(bestTime.isEmpty()) {
			response.put("error", "No available tables");
			return response;
		}
		response.put("bestTime", bestTime);
		response.put("tablesLeft", bestTime.size());
		return response;
		
	}
	
	private boolean checkAvailabilityForATable(Long idRestaurant, Integer persons, LocalDate reservationDate,
											LocalTime reservationHour) {
		Restaurant restaurant = restaurantRepository.findOne(idRestaurant);
		//TODO Handle null pointer exception
		Set<Table> tables = restaurant.getTables();
		Table bestTable = null;
		int bestSize = Integer.MAX_VALUE;
		for (Table table : tables) {
			//TODO 
			if (table.getSittingPlaces() >= persons && table.getSittingPlaces() < bestSize && 
					reservationRepository.getReservationByTable(idRestaurant, reservationDate, 
							reservationHour, table.getId()) == null) {
				bestTable = table;
				break;
			}
		}
		
		if(bestTable == null) {
			return false;
		}
		
		return true;
		
	}
	
	
	
	
	
	

}

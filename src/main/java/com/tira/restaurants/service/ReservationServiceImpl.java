package com.tira.restaurants.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
			Long idRestaurant) {

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

		if (bestTable == null) {
			return null;
		}
		
		Reservation reservation = new Reservation(persons, bestTable, userService.getCurrentUser(), reservationDate, reservationHour, restaurant);
		reservationRepository.save(reservation);
		return reservation;
		
	}

	@Override
	public List<Restaurant> getRestaurantsSortReservationsToday() {
		Set<Reservation> reservations = reservationRepository.getRestaurantsSortReservationsToday(LocalDate.now());
		List<Restaurant> restaurants = new ArrayList<>();
		for(Reservation reservation : reservations) {
			restaurants.add(reservation.getRestaurant());
		}
		return restaurants;
		
	}

}

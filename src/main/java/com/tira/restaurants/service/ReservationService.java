package com.tira.restaurants.service;

import java.time.LocalDate;
import java.time.LocalTime;import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;

public interface ReservationService{
	Reservation makeReservation(Integer persons, LocalDate reservationDate, LocalTime reservationHour,
			Long idRestaurant, Long idUser);
	Set<Restaurant> getRestaurantsSortReservationsToday();
	Map<String, Object> checkReservationAvailability(Integer persons, LocalDate date, LocalTime time, Long idRestaurant);
}

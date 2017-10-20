package com.tira.restaurants.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;

public interface ReservationService{
	Reservation makeReservation(Integer persons, LocalDate reservationDate, LocalTime reservationHour,Long idRestaurant);
	List<Restaurant> getRestaurantsSortReservationsToday();
}

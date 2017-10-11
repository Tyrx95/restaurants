package com.tira.restaurants.service;

import java.time.LocalDate;
import java.time.LocalTime;

import com.tira.restaurants.domain.Reservation;

public interface ReservationService{
	Reservation makeReservation(Integer persons, LocalDate reservationDate, LocalTime reservationHour,Long idRestaurant);
}

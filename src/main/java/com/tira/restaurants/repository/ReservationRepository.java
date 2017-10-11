package com.tira.restaurants.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	@Query("SELECT r FROM Reservation r WHERE r.restaurant.id = :idRestaurant AND r.reservationDate = :date ORDER BY r.reservationTime ASC")
	public List<Restaurant> getRestaurantsSortReservationsToday(@Param("id") Long id, @Param("date") LocalDate date);
}

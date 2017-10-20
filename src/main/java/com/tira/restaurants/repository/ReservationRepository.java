package com.tira.restaurants.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	@Query("SELECT r FROM Reservation r WHERE r.reservationDate = :date ORDER BY r.reservationTime ASC")
	public Set<Reservation> getRestaurantsSortReservationsToday(@Param("date") LocalDate date);
	
	@Query("SELECT r FROM Reservation r WHERE r.restaurant.id = :id AND r.reservationDate = :date"
			+ " AND r.reservationTime = :time AND r.table.id = :idTable")
	public Reservation getReservationByTable(@Param("id") Long id, @Param("date") LocalDate date, @Param("time") LocalTime time,
			@Param("idTable") Long idTable);
}

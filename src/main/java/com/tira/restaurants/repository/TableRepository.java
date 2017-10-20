package com.tira.restaurants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Table;

@Repository
public interface TableRepository extends CrudRepository<Table, Long> {
	
	@Query("SELECT t FROM Table t WHERE t.restaurant.id = :restaurantId ")
	List<Table> findAllTablesByRestaurantId(@Param("restaurantId") Long restaurantId);

}

package com.tira.restaurants.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Meal;


@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {
	
	@Query("SELECT m FROM Meal m WHERE m.restaurant.id = :idRestaurant AND m.type = :type ")
	public List<Meal> findMenu(@Param("idRestaurant") Long idRestaurant, @Param("type")  String type);
}

package com.tira.restaurants.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Restaurant;

@Repository
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
	
	@Query("SELECT r FROM Restaurant r WHERE r.description LIKE '%' || :text || '%'")
	public Page<Restaurant> getRestaurantsByFilter(@Param("text") String text, Pageable pageable);
}

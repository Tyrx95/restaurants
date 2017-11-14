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

	@Query("SELECT r FROM Restaurant r WHERE "
			+ "(r.description LIKE '%' || :text || '%' OR r.restaurantName LIKE '%' || :text || '%'  OR :text IS NULL or :text = '') AND"
			+ "(r.mark = :rating OR :rating IS NULL OR :rating = 0) AND "
			+ "(r.priceRange = :priceRange OR :priceRange IS NULL OR :priceRange = 0)  AND"
			+ "(r.foodType = :categories OR :categories IS NULL OR :categories = '')")
	public Page<Restaurant> getRestaurantsByFilter(@Param("text") String text, @Param("categories") String categories,
			@Param("priceRange") Integer priceRange, @Param("rating") Integer rating, Pageable pageable);
}

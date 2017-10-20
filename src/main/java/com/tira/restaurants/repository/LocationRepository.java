package com.tira.restaurants.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Location;


@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
	
	List<Location> findAll();
	
	@Query("SELECT l FROM Location l WHERE l.name LIKE '%' || :text || '%'")
	public Page<Location> getLocationsByFilter(@Param("text") String text, Pageable pageable);
}

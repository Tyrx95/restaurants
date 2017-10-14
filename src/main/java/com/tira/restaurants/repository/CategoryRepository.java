package com.tira.restaurants.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Location;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
	List<Category> findAll();
	
	@Query("SELECT c FROM Category c WHERE c.name LIKE '%' || :text || '%'")
	public Page<Category> getCategoriesByFilter(@Param("text") String text, Pageable pageable);
}

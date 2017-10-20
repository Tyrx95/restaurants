package com.tira.restaurants.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.tira.restaurants.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	
	@Query("SELECT u FROM User u WHERE lower(u.firstName) LIKE lower('%' || :text || '%')" +
			" OR lower(u.lastName) LIKE lower('%' || :text || '%')")
	public Page<User> getUsersByFilter(@Param("text") String text, Pageable pageable);
	User findByEmail(String email);
	
}

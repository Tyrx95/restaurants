package com.tira.restaurants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c WHERE c.restaurant.id = :restaurantId ")
	List<Comment> findAllCommentsByRestaurantId(@Param("restaurantId") Long restaurantId);
	
	@Query("SELECT c FROM Comment c where c.restaurant.id = :restaurantId AND c.user.id = :userId")
	Comment findCommentByRestaurantAndUser(@Param("restaurantId") Long restaurantId,@Param("userId") Long userId);
}

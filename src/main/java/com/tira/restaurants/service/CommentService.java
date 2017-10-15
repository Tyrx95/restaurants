package com.tira.restaurants.service;

import java.util.List;

import com.tira.restaurants.domain.Comment;

public interface CommentService {
	void insertComment(Integer mark, Integer idUser, Integer idRestaurant, String comment);
	List<Comment> getAllRestaurantComments(Long resturantId);
}

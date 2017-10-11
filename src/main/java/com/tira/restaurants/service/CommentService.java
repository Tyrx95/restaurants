package com.tira.restaurants.service;

public interface CommentService {
	void insertComment(Integer mark, Integer idUser, Integer idRestaurant, String comment);
}

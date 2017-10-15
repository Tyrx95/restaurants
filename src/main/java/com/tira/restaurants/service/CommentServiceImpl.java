package com.tira.restaurants.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Comment;
import com.tira.restaurants.repository.CommentRepository;
import com.tira.restaurants.repository.RestaurantRepository;
import com.tira.restaurants.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public void insertComment(Integer mark, Integer idUser, Integer idRestaurant, String comment) {
		Comment newComment = new Comment(mark, LocalDateTime.now() ,userRepository.findOne(new Long(idUser)), 
				restaurantRepository.findOne(new Long(idRestaurant)),comment);
		commentRepository.save(newComment);
	}

	@Override
	public List<Comment> getAllRestaurantComments(Long restaurantId) {
		return commentRepository.findAllCommentsByRestaurantId(restaurantId);
	}
	
	

}

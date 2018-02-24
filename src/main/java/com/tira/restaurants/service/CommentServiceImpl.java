package com.tira.restaurants.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Comment;
import com.tira.restaurants.domain.Restaurant;
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
		Restaurant restaurant = restaurantRepository.findOne(new Long(idRestaurant));
		Comment theComment = commentRepository.findCommentByRestaurantAndUser(new Long(idRestaurant), 
											new Long(idUser));
		if(theComment == null) {
			Comment newComment = new Comment(mark, LocalDateTime.now() ,userRepository.findOne(new Long(idUser)), 
					restaurant,comment);
			commentRepository.save(newComment);
		}
		else {
			theComment.setComment(comment);
			theComment.setMark(mark);
			commentRepository.save(theComment);
		}
		updateRestaurantMark(new Long(idRestaurant));
		
	}

	

	@Override
	public List<Comment> getAllRestaurantComments(Long restaurantId) {
		return commentRepository.findAllCommentsByRestaurantId(restaurantId);
	}
	
	private void updateRestaurantMark(Long idRestaurant) {
		Restaurant restaurant = restaurantRepository.findOne(new Long(idRestaurant));
		Float mark=0F;
		for(Comment comment: restaurant.getComments()) {
			mark+=comment.getMark();
		}
		restaurant.setVotes(restaurant.getComments().size());
		restaurant.setMark(Math.round(mark/restaurant.getComments().size()));
		restaurantRepository.save(restaurant);
	}

}

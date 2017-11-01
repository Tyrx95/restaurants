package com.tira.restaurants.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Comment;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Meal;
import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.domain.Table;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.repository.CommentRepository;
import com.tira.restaurants.repository.LocationRepository;
import com.tira.restaurants.repository.MealRepository;
import com.tira.restaurants.repository.ReservationRepository;
import com.tira.restaurants.repository.RestaurantRepository;
import com.tira.restaurants.repository.TableRepository;
import com.tira.restaurants.repository.UserRepository;
import com.tira.restaurants.service.ReservationService;
import com.tira.restaurants.service.RestaurantService;
import com.tira.restaurants.service.UserService;

@RestController
public class TestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private TableRepository tableRepository;
	
	@Autowired 
	private LocationRepository locationRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	



	@RequestMapping("/save")
	public void save() {
		Restaurant res = restaurantService.getOne(161L);
		Table table = new Table(res, 4);
		tableRepository.save(table);
		Reservation reservation = new Reservation(4, table, userService.getCurrentUser(), LocalDate.now(), LocalTime.now(),res);
		reservationRepository.save(reservation);
		
	}
	
//	@RequestMapping("/addAdminTyrx")
//	public String addAdmin() {
//		User user = new User("amartiric@gmail.com","062932990","Bosnia","Zenica","Amar","Tiric","tiralav95");
//		userService.saveAdmin(user);
//	
//		return "Success";
//	}
//	

	
}

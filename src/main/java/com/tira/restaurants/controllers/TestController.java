package com.tira.restaurants.controllers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	


//	@RequestMapping("/save")
//	public String process() {
//		
//		Location loc = new Location("Zenica", 1);
//		locationRepository.save(loc);
//		Set<Category> cats = new HashSet<>();
//		cats.add(new Category("Chicken"));
//		System.out.println("Creating Restaurant");
//		Restaurant res = new Restaurant("iChicken","good.",123.22,122.34, 4, 6, 5, "ifn.jpg", "cfn.jpg", loc, "Chicken", cats);
//		System.out.println("Restaurant created");
//		restaurantRepository.save(res);
//		System.out.println("Restaurant saved on repo");
//		Meal meal1 = new Meal(res, "Bosnian", "Sarma druze", 666.0, "veri gut");
//		mealRepository.save(meal1);
//		User user = new User("tt@gmail.com","062222","Bosnia","Zenica","Tira","tiric","");
//		userRepository.save(user);
//		Comment com = new Comment(1, LocalDateTime.MAX, user, res);
//		commentRepository.save(com);
//		Table table = new Table(res, 4);
//		tableRepository.save(table);
//		Reservation reservation = new Reservation(4, table, user, LocalDateTime.MAX);
//		reservationRepository.save(reservation);
//		return "Success";
//	}
	
//	@RequestMapping("/addAdminTyrx")
//	public String addAdmin() {
//		User user = new User("amartiric@gmail.com","062932990","Bosnia","Zenica","Amar","Tiric","tiralav95");
//		userService.saveAdmin(user);
//	
//		return "Success";
//	}
//	
	
	@RequestMapping("/addTable")
	public String addTable() {
		Restaurant restaurant = restaurantService.getOne(32L);
		Table table4 = new Table(restaurant, 4);
		Table table5 = new Table(restaurant, 5);
		Table table2 = new Table(restaurant, 2);
		tableRepository.save(table4);
		tableRepository.save(table5);
		tableRepository.save(table2);
		return "Success";
	}
	
	
}

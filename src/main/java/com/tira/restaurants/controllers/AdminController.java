package com.tira.restaurants.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tira.restaurants.domain.Category;
import com.tira.restaurants.domain.Comment;
import com.tira.restaurants.domain.Location;
import com.tira.restaurants.domain.Restaurant;
import com.tira.restaurants.domain.Table;
import com.tira.restaurants.domain.User;
import com.tira.restaurants.dto.CategoryDTO;
import com.tira.restaurants.dto.CommentDTO;
import com.tira.restaurants.dto.ErrorMessage;
import com.tira.restaurants.dto.FilterDTO;
import com.tira.restaurants.dto.LocationFilterResponseDTO;
import com.tira.restaurants.dto.MealResponseDTO;
import com.tira.restaurants.dto.RestaurantEditDTO;
import com.tira.restaurants.dto.RestaurantRequestDTO;
import com.tira.restaurants.dto.RestaurantResponseDTO;
import com.tira.restaurants.dto.TableDTO;
import com.tira.restaurants.dto.UserEditDTO;
import com.tira.restaurants.dto.UserRegisterDTO;
import com.tira.restaurants.dto.UserResponseDTO;
import com.tira.restaurants.service.CategoryService;
import com.tira.restaurants.service.CommentService;
import com.tira.restaurants.service.LocationService;
import com.tira.restaurants.service.MealService;
import com.tira.restaurants.service.ModelMapperService;
import com.tira.restaurants.service.RestaurantService;
import com.tira.restaurants.service.TableService;
import com.tira.restaurants.service.UserService;

@Controller
@RequestMapping(value="/admin")
@SuppressWarnings("rawtypes")
public class AdminController {
	
	@Autowired 
	RestaurantService restaurantService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired 
	UserService userService;	
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	TableService tableService;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	@Autowired
	MealService mealService;
	
	
	@RequestMapping(value = "/getFilteredLocations", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getFilteredLocations(@RequestBody FilterDTO filter)  {
		PageRequest pageReq = new PageRequest(filter.getPageNumber()-1, filter.getItemsPerPage());
		String searchText = filter.getSearchText();
		Page<Location> locationPages = locationService.getByFilter(searchText,pageReq);
		List<LocationFilterResponseDTO> locationsDTO = new ArrayList<>();
		
		for(Location location : locationPages.getContent()) {
			locationsDTO.add(modelMapperService.convertToLocationFilterResponseDto(location));
		}
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("locations", locationsDTO);
		responseBody.put("numberOfPages", locationPages.getTotalPages());
    	return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	
    }
	
	@RequestMapping(value = "/getAdministrationCounters", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getAdministrationCounters()  {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("restaurantsNumber", restaurantService.getRestaurantsCount());
		responseBody.put("locationsNumber", locationService.getLocationCount());
		responseBody.put("usersNumber", userService.getUserCount());
		
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
	
	@RequestMapping(value = "/addLocation", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity addLocation(@RequestBody Map<String, Object> requestBody)  {
		Location location = new Location((String) requestBody.get("name"),0);
		locationService.addLocation(location);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToLocationFilterResponseDto(location));
    }
	
	@RequestMapping(value = "/editLocation", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity editLocation(@RequestBody LocationFilterResponseDTO newLocation)  {
		Location location = locationService.editLocation(newLocation.getId(),newLocation.getName());
		if(location == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Location doesn't exist!"));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToLocationFilterResponseDto(location));
		}
    }
	
	@RequestMapping(value = "/deleteLocation", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity deleteLocation(@RequestBody Map<String, Object> requestBody)  {
		locationService.deleteLocation(convertToLong(requestBody.get("id")));
		return ResponseEntity.status(HttpStatus.OK).body("");
    }
	
	@RequestMapping(value = "/getLocationDetails", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getLocationDetails(@RequestBody Map<String, Object> requestBody)  {
		Location location = locationService.getLocation(convertToLong(requestBody.get("id")));		
		if(location == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToLocationFilterResponseDto(location));
    }
	
	@RequestMapping(value = "/getFilteredCategories", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getFilteredCategories(@RequestBody FilterDTO filter)  {
		PageRequest pageReq = new PageRequest(filter.getPageNumber()-1, filter.getItemsPerPage());
		String searchText = filter.getSearchText();
		Page<Category> categoryPages = categoryService.getByFilter(searchText,pageReq);
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		
		for(Category category : categoryPages.getContent()) {
			categoriesDTO.add(modelMapperService.convertToCategoryDto(category));
		}
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("categories", categoriesDTO);
		responseBody.put("numberOfPages", categoryPages.getTotalPages());
    	return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	
    }
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity addCategory(@RequestBody Map<String, Object> requestBody)  {
		Category category = new Category((String) requestBody.get("name"));
		categoryService.addCategory(category);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToCategoryDto(category));
	
    }
	
	@RequestMapping(value = "/editCategory", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity editCategory(@RequestBody CategoryDTO editedCategory)  {
		Category category = categoryService.editCategory(editedCategory.getId(),editedCategory.getName());
		if(category == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Category doesn't exist!"));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToCategoryDto(category));
		}
    }
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity deleteCategory(@RequestBody Map<String, Object> requestBody)  {
		categoryService.deleteCategory(convertToLong(requestBody.get("id")));
		return ResponseEntity.status(HttpStatus.OK).body("");
    }
	
	@RequestMapping(value = "/getCategoryDetails", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getCategoryDetails(@RequestBody Map<String, Object> requestBody)  {
		Category category = categoryService.getCategory(convertToLong(requestBody.get("id")));
		if(category == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToCategoryDto(category));
    }
	
	@RequestMapping(value = "/getFilteredRestaurants", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity getFilteredRestaurants(@RequestBody FilterDTO filter)  {
		PageRequest pageReq = new PageRequest(filter.getPageNumber()-1, filter.getItemsPerPage());
		String searchText = filter.getSearchText();
		Page<Restaurant> restaurantPages = restaurantService.getByFilter(searchText,pageReq);
		List<RestaurantResponseDTO> restaurantsDTO = new ArrayList<>();
		
		for(Restaurant restaurant : restaurantPages.getContent()) {
			restaurantsDTO.add(modelMapperService.convertToRestaurantDto(restaurant));
		}
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("restaurants", restaurantsDTO);
		responseBody.put("numberOfRestaurantPages", restaurantPages.getTotalPages());
    	return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
	
	@RequestMapping(value = "/addRestaurant", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequestDTO restaurantDTO)  {
		Restaurant restaurant = modelMapperService.convertToRestaurantEntity(restaurantDTO);
		restaurantService.addRestaurant(restaurant);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToRestaurantDto(restaurant));
    }
	
	@RequestMapping(value = "/editRestaurant", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity editRestaurant(@RequestBody RestaurantEditDTO restaurantDTO)  {
		if(restaurantService.editRestaurant(restaurantDTO)) {
			return ResponseEntity.status(HttpStatus.OK).body(restaurantDTO);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
    }
	
	@RequestMapping(value = "/deleteRestaurant", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity deleteRestaurant(@RequestBody Map<String, Object> requestBody)  {
		restaurantService.deleteRestaurant(convertToLong(requestBody.get("id")));
		return ResponseEntity.status(HttpStatus.OK).body("");
    }
	
	@RequestMapping(value = "/getAllRestaurantComments", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getAllRestaurantComments(@RequestBody Map<String, Object> requestBody)  {
		List<Comment> comments= commentService.getAllRestaurantComments(convertToLong(requestBody.get("idRestaurant")));
		List<CommentDTO> commentsDTO = new ArrayList<>();
		
		for(Comment comment: comments) {
			commentsDTO.add(modelMapperService.convertToCommentDTO(comment));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(commentsDTO);
    }
	

	@RequestMapping(value = "/getRestaurantCategories", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getRestaurantCategories(@RequestBody Map<String, Object> requestBody)  {
		Set<Category> categories= restaurantService.getRestaurantCategories((convertToLong(requestBody.get("id"))));
		if(categories==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		
		for(Category category: categories) {
			categoriesDTO.add(modelMapperService.convertToCategoryDto(category));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(categoriesDTO);
    }
	
	@RequestMapping(value = "/getFilteredUsers", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getFilteredUsers(@RequestBody FilterDTO filter)  {
		PageRequest pageReq = new PageRequest(filter.getPageNumber()-1, filter.getItemsPerPage());
		String searchText = filter.getSearchText();
		Page<User> userPages = userService.getByFilter(searchText,pageReq);
		List<UserResponseDTO> usersDTO = new ArrayList<>();
		
		for(User user : userPages.getContent()) {
			usersDTO.add(modelMapperService.convertToUserDto(user));
		}
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("users", usersDTO);
		responseBody.put("numberOfPages", userPages.getTotalPages());
    	return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity addUser(@RequestBody UserRegisterDTO userDTO)  {
		User user = modelMapperService.convertToUserEntity(userDTO);
		userService.save(user);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToUserDto(user));
    }
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity editUser(@RequestBody UserEditDTO userDTO)  {
		User editedUser = userService.editUser(userDTO);
		if(editedUser== null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("User does not exist!"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToUserDto(editedUser));
    }
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity deleteUser(@RequestBody Map<String, Object> requestBody)  {
		userService.deleteUser((convertToLong(requestBody.get("idRestaurant"))));
		return ResponseEntity.status(HttpStatus.OK).body("");
    }
	
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getUserDetails(@RequestBody Map<String, Object> requestBody)  {
		User user = userService.getUser((convertToLong(requestBody.get("idRestaurant"))));
		if(user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToUserDto(user));
    }
	
	@RequestMapping(value = "/getAllRestaurantTables", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity getAllRestaurantTables(@RequestBody Map<String, Object> requestBody)  {
		List<Table> tables= tableService.getAllRestaurantTables((convertToLong(requestBody.get("idRestaurant"))));
		List<TableDTO> tablesDTO = new ArrayList<>();
		
		for(Table table: tables) {
			tablesDTO.add(modelMapperService.convertToTableDTO(table));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(tablesDTO);
    }
	
	@RequestMapping(value = "/adminTableItems", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity adminTableItems(@RequestBody Map<String, Object> requestBody)  {
		List<LinkedHashMap> addTablesLHM= (List<LinkedHashMap>) requestBody.get("addQueue");
		List<LinkedHashMap> editTablesLHM= (List<LinkedHashMap>) requestBody.get("editQueue");
		List<LinkedHashMap> deleteTablesLHM= (List<LinkedHashMap>) requestBody.get("deleteQueue");
		
		if(addTablesLHM!=null && !addTablesLHM.isEmpty()) {
			
			tableService.addTables(convertToTablesDTO(addTablesLHM));
		}
		if(editTablesLHM!=null && !editTablesLHM.isEmpty()) {
			tableService.editTables(convertToTablesDTO(editTablesLHM));
		}
		if(deleteTablesLHM!=null && !deleteTablesLHM.isEmpty()) {
			
			tableService.deleteTables(convertToTablesDTO(deleteTablesLHM));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	@RequestMapping(value = "/adminMenuItems", method = RequestMethod.POST, consumes = "application/json",produces="application/json")
    public ResponseEntity adminMenuItems(@RequestBody Map<String, Object> requestBody)  {
		List<LinkedHashMap> addMealsLHM= (List<LinkedHashMap>) requestBody.get("addQueue");
		List<LinkedHashMap> editMealsLHM= (List<LinkedHashMap>) requestBody.get("editQueue");
		List<LinkedHashMap> deleteMealsLHM= (List<LinkedHashMap>) requestBody.get("deleteQueue");
		
		if(addMealsLHM!=null && !addMealsLHM.isEmpty()) {
			mealService.addMeals(convertToMealsDTO(addMealsLHM));
		}
		if(editMealsLHM!=null && !editMealsLHM.isEmpty()) {
			mealService.editMeals(convertToMealsDTO(editMealsLHM));
		}
		if(deleteMealsLHM!=null && !deleteMealsLHM.isEmpty()) {
			
			mealService.deleteMeals(convertToMealsDTO(deleteMealsLHM));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	

	
	private List<MealResponseDTO> convertToMealsDTO(List<LinkedHashMap> mealsLHM) {
		List<MealResponseDTO> mealsDTO = new ArrayList<>();
		for(LinkedHashMap lhm: mealsLHM) {
			MealResponseDTO mealDTO = modelMapperService.convertFromHashMapToMealsDTO(lhm);
			mealsDTO.add(mealDTO);
		}
		
		return mealsDTO;
	}

	private List<TableDTO> convertToTablesDTO(List<LinkedHashMap> tablesLHM) {
		List<TableDTO> tablesDTO = new ArrayList<>();
		for(LinkedHashMap lhm: tablesLHM) {
			TableDTO tableDTO = modelMapperService.convertFromHashMapToTablesDTO(lhm);
			tablesDTO.add(tableDTO);
		}
		
		return tablesDTO;
	}
	
	private Long convertToLong(Object object) {
		Long id;
		try{
			id = new Long((Integer) object);
		}
		catch(ClassCastException e) {
			id= new Long(Integer.parseInt((String) object));
		}
		return id;
	}
	
}

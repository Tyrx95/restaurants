package com.tira.restaurants.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "restaurants")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "restaurantName")
	private String restaurantName;

	@Column(name = "description")
	private String description;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "mark")
	private Integer mark;

	@Column(name = "votes")
	private Integer votes;

	@Column(name = "priceRange")
	private Integer priceRange;

	@Column(name = "imageFileName")
	private String imageFileName;

	@Column(name = "coverFileName")
	private String coverFileName;

	@Column(name = "foodType")
	private String foodType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location")
	private Location location;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Meal> meals;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<com.tira.restaurants.domain.Table> tables;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<com.tira.restaurants.domain.Reservation> reservations;
	
	@ManyToMany(cascade = { 
		    CascadeType.PERSIST, 
		    CascadeType.MERGE
		})
	@JoinTable(name = "res_cat",
		    joinColumns = @JoinColumn(name = "res_id"),
		    inverseJoinColumns = @JoinColumn(name = "cat_id")
		)
	private Set<Category> categories = new HashSet<>();
	
	protected Restaurant() {
	}

	public Restaurant(String restaurantName, String description, Double latitude, Double longitude, Integer mark,
			Integer votes, Integer priceRange, String imageFileName, String coverFileName, Location location,
			 Set<Category> categories) {
		super();
		this.restaurantName = restaurantName;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mark = mark;
		this.votes = votes;
		this.priceRange = priceRange;
		this.imageFileName = imageFileName;
		this.coverFileName = coverFileName;
		this.location = location;
		this.categories = categories;
		updateFoodType();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(Integer priceRange) {
		this.priceRange = priceRange;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getCoverFileName() {
		return coverFileName;
	}

	public void setCoverFileName(String coverFileName) {
		this.coverFileName = coverFileName;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}

	public void addMeal(Meal meal) {
		meals.add(meal);
		meal.setRestaurant(this);
	}

	public void removeMeal(Meal meal) {
		meals.remove(meal);
		meal.setRestaurant(null);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setRestaurant(this);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setRestaurant(null);
	}
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
		updateFoodType();
	}

	public void addCategory(Category category) {
		categories.add(category);
		category.getRestaurants().add(this);
		updateFoodType();
	}

	public void removeCategory(Category category) {
		categories.remove(category);
		category.getRestaurants().remove(this);
		updateFoodType();
	}	
	
	public Set<com.tira.restaurants.domain.Table> getTables() {
		return tables;
	}

	public void setTables(Set<com.tira.restaurants.domain.Table> tables) {
		this.tables = tables;
	}
	
	public void addTable(com.tira.restaurants.domain.Table table) {
		tables.add(table);
		table.setRestaurant(this);
	}

	public void removeTable(com.tira.restaurants.domain.Table table) {
		tables.remove(table);
		table.setRestaurant(null);
	}
	
	public Set<com.tira.restaurants.domain.Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<com.tira.restaurants.domain.Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addReservation(com.tira.restaurants.domain.Reservation reservation) {
		reservations.add(reservation);
		reservation.setRestaurant(this);
	}

	public void removeReservation(com.tira.restaurants.domain.Reservation reservation) {
		reservations.remove(reservation);
		reservation.setRestaurant(null);
	}

	private void updateFoodType() {
		foodType="";
		for(Category category : categories) {
			foodType.concat(" | "+category.getName());
		}
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", restaurantName=" + restaurantName + ", description=" + description
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", mark=" + mark + ", votes=" + votes
				+ ", priceRange=" + priceRange + ", imageFileName=" + imageFileName + ", coverFileName=" + coverFileName
				+ ", foodType=" + foodType + ", location=" + location + "]";
	}

}

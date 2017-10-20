package com.tira.restaurants.dto;

public class RestaurantResponseDTO {
	
	private Long id;
	private String restaurantName;
	private String description;
	private Double latitude;
	private Double longitude;
	private Integer mark;
	private Integer votes;
	private Integer priceRange;
	private String imageFileName;
	private String coverFileName;
	private String foodType;
	private Long location;
	
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
	public Long getLocation() {
		return location;
	}
	public void setLocation(Long location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "RestaurantResponseDTO [id=" + id + ", restaurantName=" + restaurantName + ", description=" + description
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", mark=" + mark + ", votes=" + votes
				+ ", priceRange=" + priceRange + ", imageFileName=" + imageFileName + ", coverFileName=" + coverFileName
				+ ", foodType=" + foodType + ", location=" + location + "]";
	}
	
	
	
}

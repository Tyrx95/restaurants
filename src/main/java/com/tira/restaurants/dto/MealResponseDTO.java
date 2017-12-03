package com.tira.restaurants.dto;

public class MealResponseDTO {
	
	private Long id;
	private Long idRestaurant;
	private String type;
	private String name;
	private Double price;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MealResponseDTO{" +
				"id=" + id +
				", idRestaurant=" + idRestaurant +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				'}';
	}
}

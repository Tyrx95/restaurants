package com.tira.restaurants.dto;

public class TableDTO {
	
	private Long id;
	private Long idRestaurant;
	private Integer sittingPlaces;
	
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
	public Integer getSittingPlaces() {
		return sittingPlaces;
	}
	public void setSittingPlaces(Integer sittingPlaces) {
		this.sittingPlaces = sittingPlaces;
	}
	@Override
	public String toString() {
		return "TableDTO [id=" + id + ", idRestaurant=" + idRestaurant + ", sittingPlaces=" + sittingPlaces + "]";
	}
	
	
		
}

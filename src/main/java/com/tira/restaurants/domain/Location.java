package com.tira.restaurants.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", unique=true)
	private String name;

	@Column(name = "number")
	private Integer number;

	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	private Set<Restaurant> restaurants;

	protected Location() {
	}

	public Location(String name, Integer number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", number=" + number + ", restaurants=" + restaurants + "]";
	}
	
	

}

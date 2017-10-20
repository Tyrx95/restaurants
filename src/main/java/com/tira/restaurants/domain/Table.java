package com.tira.restaurants.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Reservation> reservations;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRestaurant")
	private Restaurant restaurant;

	@Column(name = "sittingPlaces")
	private Integer sittingPlaces;

	protected Table() {
	}

	public Table(Restaurant restaurant, Integer sittingPlaces) {
		super();
		this.restaurant = restaurant;
		this.sittingPlaces = sittingPlaces;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Integer getSittingPlaces() {
		return sittingPlaces;
	}

	public void setSittingPlaces(Integer sittingPlaces) {
		this.sittingPlaces = sittingPlaces;
	}

	public Set<com.tira.restaurants.domain.Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<com.tira.restaurants.domain.Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addReservation(com.tira.restaurants.domain.Reservation reservation) {
		reservations.add(reservation);
		reservation.setTable(this);
	}

	public void removeReservation(com.tira.restaurants.domain.Reservation reservation) {
		reservations.remove(reservation);
		reservation.setTable(null);
	}

}

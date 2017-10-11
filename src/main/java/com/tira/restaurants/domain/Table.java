package com.tira.restaurants.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Reservation reservation;

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

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
		reservation.setTable(this);
	}

	public Reservation getReservation() {
		return reservation;
	}

	@Override
	public String toString() {
		return "Table [id=" + id + ", reservation=" + reservation + ", restaurant=" + restaurant + ", sittingPlaces="
				+ sittingPlaces + "]";
	}

}

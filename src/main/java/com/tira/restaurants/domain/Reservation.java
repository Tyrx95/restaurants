package com.tira.restaurants.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name="persons")
	private Integer persons;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTable")
    private com.tira.restaurants.domain.Table table;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRestaurant")
	private Restaurant restaurant;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "reservationDate")
	private LocalDate reservationDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@Column(name = "reservationTime")
	private LocalTime reservationTime;
	
	protected Reservation() {}

	public Reservation(Integer persons, com.tira.restaurants.domain.Table table, User user, LocalDate reservationDate, LocalTime reservationTime ,Restaurant restaurant) {
		super();
		this.persons = persons;
		this.table = table;
		this.user = user;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	public com.tira.restaurants.domain.Table getTable() {
		return table;
	}

	public void setTable(com.tira.restaurants.domain.Table table) {
		this.table = table;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	
	
}

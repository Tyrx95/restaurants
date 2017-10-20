package com.tira.restaurants.dto;

import java.time.LocalDateTime;

public class SuccessfulReservationDTO {
	
	private Long id;
	private Long idTable;
	private Long idUser;
	private Integer persons;
	private LocalDateTime reservationDateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdTable() {
		return idTable;
	}
	public void setIdTable(Long idTable) {
		this.idTable = idTable;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Integer getPersons() {
		return persons;
	}
	public void setPersons(Integer persons) {
		this.persons = persons;
	}
	public LocalDateTime getReservationDateTime() {
		return reservationDateTime;
	}
	public void setReservationDateTime(LocalDateTime reservationDateTime) {
		this.reservationDateTime = reservationDateTime;
	}
	
}

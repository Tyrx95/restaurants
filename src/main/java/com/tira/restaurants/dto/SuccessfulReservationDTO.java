package com.tira.restaurants.dto;

import java.time.LocalDateTime;

public class SuccessfulReservationDTO {
	
	private Long id;
	private Long idTable;
	private Long idUser;
	private Integer persons;
	private LocalDateTime reservationDateTime;
}

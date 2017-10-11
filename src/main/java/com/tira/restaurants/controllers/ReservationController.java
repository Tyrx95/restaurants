package com.tira.restaurants.controllers;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tira.restaurants.domain.Reservation;
import com.tira.restaurants.dto.SuccessfulReservationDTO;
import com.tira.restaurants.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@RequestMapping(value = "/makeReservation", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity makeReservation(@RequestBody Map<String, Object> body)  {
		Reservation reservation = reservationService.makeReservation((Integer) body.get("persons"), 
					(LocalDate)body.get("reservationDate"),(LocalTime) body.get("reservationHour"), (Long) body.get("idRestaurant"));
		if(reservation!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(convertToResponseSuccessfulReservationDTO(reservation));
		}	
			
		return null;
    }

	private SuccessfulReservationDTO convertToResponseSuccessfulReservationDTO(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

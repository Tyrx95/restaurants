package com.tira.restaurants.controllers;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import com.tira.restaurants.dto.ErrorMessage;
import com.tira.restaurants.dto.SuccessfulReservationDTO;
import com.tira.restaurants.service.ModelMapperService;
import com.tira.restaurants.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	@RequestMapping(value = "/makeReservation", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity makeReservation(@RequestBody Map<String, Object> body)  {
		Reservation reservation = reservationService.makeReservation((Integer) body.get("persons"), 
						LocalDate.parse((String)body.get("reservationDate"), DateTimeFormatter.ofPattern("MMM dd, yyyy")),
						LocalTime.parse((String)body.get("reservationHour"), DateTimeFormatter.ofPattern("hh:mm a")), 
						new Long((Integer) body.get("idRestaurant")));
		if(reservation!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToResponseSuccessfulReservationDTO(reservation));
		}	
			
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("No available tables"));
		}
    }
	
	
	
	

}

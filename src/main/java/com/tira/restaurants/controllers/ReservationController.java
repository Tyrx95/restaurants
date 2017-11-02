package com.tira.restaurants.controllers;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		
		String persons = (String) body.get("persons");
		Integer numPersons =Integer.parseInt(persons.split("\\s+")[0]);
		String date = ((String) body.get("reservationDate")).split("T")[0];
		LocalDate _date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Reservation reservation = reservationService.makeReservation(numPersons, 
						_date,
						LocalTime.parse((String)body.get("reservationHour"), DateTimeFormatter.ofPattern("hh:mm a")), 
						new Long((Integer) body.get("idRestaurant")),
						new Long((Integer) body.get("idUser")));
		if(reservation!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapperService.convertToResponseSuccessfulReservationDTO(reservation));
		}	
			
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("No available tables"));
		}
		
    }
	
	@RequestMapping(value = "/checkReservationAvailability", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<Map<String,Object>> checkReservationAvailability(@RequestBody Map<String, Object> body)  {
		String persons = (String) body.get("people");
		Integer numPersons =Integer.parseInt(persons.split("\\s+")[0]);
		String dateString = ((String) body.get("date")).split("T")[0];
		LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalTime time = LocalTime.parse((String)body.get("hour"), DateTimeFormatter.ofPattern("hh:mm a"));
		Long idRestaurant = new Long((Integer) body.get("idRestaurant"));
		
		Map<String, Object> responseBody = reservationService.checkReservationAvailability(numPersons,date,time,idRestaurant);
		if(responseBody.get("error")!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
		
    }
	
	
	
	
	

}

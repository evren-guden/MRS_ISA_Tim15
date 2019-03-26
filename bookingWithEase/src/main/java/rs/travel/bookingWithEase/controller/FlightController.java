package rs.travel.bookingWithEase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.service.FlightService;

@Controller
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> kreirajServis(@RequestBody Flight flight) throws Exception {
		Flight saved = service.create(flight);
		return new ResponseEntity<Flight>(saved, HttpStatus.CREATED);
	}
}

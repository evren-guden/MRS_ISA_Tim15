package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.service.FlightService;

import rs.travel.bookingWithEase.service.IFlightService;
@RestController
@Controller
@RequestMapping("/flights")
public class FlightController {

	
	@Autowired
	@Qualifier("flightService")
	private FlightService service;
	protected IFlightService service1;
	
	
	
	
	@RequestMapping(value = "/deliveryAll", method = RequestMethod.GET)
	public Collection<Flight> deliveryFlight() {
		return service1.deliveryFlight();
	}

	@RequestMapping(value = "/add")
	public boolean addFlight(@RequestBody Flight newFlight) {
		return service1.addFlight(newFlight);
	}	
	
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> kreirajServis(@RequestBody Flight flight) throws Exception {
		Flight saved = service.create(flight);
		return new ResponseEntity<Flight>(saved, HttpStatus.CREATED);
		
	
		
	}
}

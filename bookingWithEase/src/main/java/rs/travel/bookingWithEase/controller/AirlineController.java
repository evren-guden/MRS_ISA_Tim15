package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.service.AirlineService;

@RestController
@RequestMapping(value = "/airlines")
public class AirlineController {
	
	@Autowired
	private AirlineService airlineService;
	
	@PreAuthorize("hasRole('ADMINAIRLINE')")
	@GetMapping("/secured/all")
	public String securedHello() {
		return "Secured Hello";
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Airline>> getAll() {
		Collection<Airline> airlines = airlineService.findAll();

		return new ResponseEntity<Collection<Airline>>(airlines, HttpStatus.OK);
	}
	
	@PostMapping(value="/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> update(@RequestBody Airline airline){
		System.out.println("update" + airline.getId());
		Airline updatedAirline = null;
		try {
			updatedAirline = airlineService.update(airline);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(updatedAirline == null) {
			return new ResponseEntity<Airline>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Airline>(updatedAirline, HttpStatus.OK);
	}
	
	
}

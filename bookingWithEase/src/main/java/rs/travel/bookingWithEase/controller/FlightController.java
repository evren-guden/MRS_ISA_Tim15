package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private FlightService flightService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Flight>> getAll(){
		
		Collection<Flight> flights = flightService.findAll();

		return new ResponseEntity<Collection<Flight>>(flights, HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> create(@RequestBody Flight flight){
		
		Flight flig= null;
		try {
			flig = flightService.save(flight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(flig == null) {
			return new ResponseEntity<Flight>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Flight>(flig, HttpStatus.OK);
	}
	
	
	
	/*@RequestMapping(value = "/createFlight", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> create(@RequestBody FlightDTO flightDTO) {
		Flight retVal = flightService.create(new Flight(flightDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}*/
	
	
	
	
	
	
	
	
	@PostMapping(value="/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> update(@RequestBody Flight flight){
		
		Flight flig = null;
		try {
			flig = flightService.save(flight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(flig == null) {
			return new ResponseEntity<Flight>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Flight>(flig, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
public ResponseEntity<Void> deleteFlight(@PathVariable Long id){
		
		Optional<Flight> flight = flightService.findOne(id);
		
		if (flight != null){
			flightService.delete(id);;
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
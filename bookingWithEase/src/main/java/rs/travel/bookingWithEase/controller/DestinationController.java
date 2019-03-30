package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Destination;

import rs.travel.bookingWithEase.service.DestinationService;

@RestController
@RequestMapping(value = "/destination")
public class DestinationController {
	
	
	@Autowired
	private DestinationService dService;
	
	
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Destination>> getAll() {
		Collection<Destination> destinations = dService.findAll();

		return new ResponseEntity<Collection<Destination>>(destinations, HttpStatus.OK);
	}
	
	
	
	
	
	
	@PostMapping(value="/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Destination> update(@RequestBody Destination destination){
		System.out.println("update" + destination.getIdAerodromes());
		Destination updatedDestination = null;
		try {
			updatedDestination = dService.update(destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(updatedDestination == null) {
			return new ResponseEntity<Destination>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Destination>(updatedDestination, HttpStatus.OK);
	}
	

}

package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Destination> create(@RequestBody Destination destination){
		
		Destination des = null;
		try {
			des = dService.save(destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(des == null) {
			return new ResponseEntity<Destination>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Destination>(des, HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(value="/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Destination> update(@RequestBody Destination destination){
		
		Destination des= null;
		try {
			des = dService.save(destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(des == null) {
			return new ResponseEntity<Destination>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Destination>(des, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteDestination(@PathVariable Long id){
			
			Optional<Destination> destination = dService.findOne(id);
			
			if (destination != null){
				dService.delete(id);;
				return new ResponseEntity<>(HttpStatus.OK);
			} else {		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

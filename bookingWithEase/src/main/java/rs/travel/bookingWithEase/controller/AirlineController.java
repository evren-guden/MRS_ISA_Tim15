package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.dto.AirlineDTO;
import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Branch;
import rs.travel.bookingWithEase.model.Location;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.service.AirlineService;
import rs.travel.bookingWithEase.service.FlightService;
@CrossOrigin
@RestController
@RequestMapping(value = "/airlines")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	@Autowired
	private FlightService flightService;

	@PreAuthorize("hasRole('ADMINAIRLINE')")
	@GetMapping("/secured/all")
	public String securedHello() {
		return "Secured Hello";
	}

	@GetMapping("/secured/alternate")
	public String alternate() {
		return "alternate";
	}

	
	 @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) public
	  ResponseEntity<Collection<Airline>> getAll() {
	  
	  Collection<Airline> airlines = airlineService.findAll();
	  
	  return new ResponseEntity<Collection<Airline>>(airlines, HttpStatus.OK); }




	@RequestMapping(value="/airlines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Airline>> getAirlines() {
		List<Airline> airlines = airlineService.findAll();
		return new ResponseEntity<List<Airline>>(airlines, HttpStatus.OK);
	}
	
	

	
	/*@RequestMapping(value = "/airlines/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> getAirline(@PathVariable("id") Long id) {
		Airline airline = airlineService.findOne(id).get();
		if(airline == null)
			return new ResponseEntity<Airline>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Airline>(airline, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "airlines/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AirlineDTO> getAirline(@PathVariable("id") Long id) {
		Airline airline = airlineService.findOne(id).get();
		if (airline == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
		return new ResponseEntity<>(new AirlineDTO(airline), HttpStatus.OK);}

	@RequestMapping(value = "/airlines", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAirline(@RequestBody Airline airline) {

		if (airlineService.findOneByName(airline.getName()) == null) {
			Airline returnAirline = airlineService.save(airline);
			return new ResponseEntity<>(returnAirline, HttpStatus.CREATED);
		}

		return new ResponseEntity<>("Airline with that name already exists!", HttpStatus.FORBIDDEN);
	}


	
	@PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> update(Airline airline){
		
		Airline air = airlineService.save(airline);

		return new ResponseEntity<Airline>(air, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Airline> deleteAirline(@PathVariable("id") Long id) {
		airlineService.delete(id);
		return new ResponseEntity<Airline>(HttpStatus.NO_CONTENT);
	}

}

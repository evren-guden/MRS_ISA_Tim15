package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.service.VehicleService;

@Controller
@RequestMapping(value="/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Vehicle>> getAll(){
		
		Collection<Vehicle> vehicles = service.findAll();
		
		return new ResponseEntity<Collection<Vehicle>>(vehicles, HttpStatus.OK);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle){
		
		Vehicle veh = null;
		try {
			veh = service.update(vehicle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(veh == null) {
			return new ResponseEntity<Vehicle>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Vehicle>(veh, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/initial", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Vehicle>> initial() {
		service.initValues();
		Collection<Vehicle> vehicles = service.findAll();

		return new ResponseEntity<Collection<Vehicle>>(vehicles, HttpStatus.OK);
	}
}

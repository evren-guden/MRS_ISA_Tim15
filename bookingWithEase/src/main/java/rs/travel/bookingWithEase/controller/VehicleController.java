package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.service.VehicleService;

@Controller
@RequestMapping(value="/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Vehicle>> getAll(){
		
		Collection<Vehicle> vehicles = vehicleService.findAll();

		return new ResponseEntity<Collection<Vehicle>>(vehicles, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMINRAC')")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle){
		System.out.println("add vehicle");
		Vehicle veh = null;
		try {
			veh = vehicleService.save(vehicle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(veh == null) {
			return new ResponseEntity<Vehicle>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Vehicle>(veh, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMINRAC')")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle){
		
		Vehicle veh = null;
		try {
			veh = vehicleService.save(vehicle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(veh == null) {
			return new ResponseEntity<Vehicle>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Vehicle>(veh, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> findOne(@PathVariable("id") Long id){
		Optional<Vehicle> veh = vehicleService.findOne(id);
		
		return new ResponseEntity<Vehicle>(veh.get(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMINRAC')")
	@DeleteMapping(value="/{id}")
public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id){
		
		Optional<Vehicle> vehicle = vehicleService.findOne(id);
		if (vehicle != null){
			vehicleService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

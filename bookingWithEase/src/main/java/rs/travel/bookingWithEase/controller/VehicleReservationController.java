package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.model.VehicleReservation;
import rs.travel.bookingWithEase.service.VehicleReservationService;

@Controller
@RequestMapping(value="/vehicleReservations")
public class VehicleReservationController {

	@Autowired
	private VehicleReservationService vehResService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<VehicleReservation>> getAll(){
		
		Collection<VehicleReservation> vehRess = vehResService.findAll();
		
		return new ResponseEntity<Collection<VehicleReservation>>(vehRess, HttpStatus.OK);
	}
	
	/*@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehicleReservation> create(@RequestBody VehicleReservation vehRes){
		
		VehicleReservation vehicleReservation = null;
		
		try {
			vehicleReservation = vehResService.save(vehRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(vehicleReservation == null) {
			return new ResponseEntity<VehicleReservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<VehicleReservation>(vehicleReservation, HttpStatus.OK);
	}*/
}

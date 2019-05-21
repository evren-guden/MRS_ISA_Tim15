package rs.travel.bookingWithEase.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.model.VehicleReservation;
import rs.travel.bookingWithEase.service.VehicleReservationService;

@Controller
@RequestMapping(value = "/vehicleReservations")
public class VehicleReservationController {

	@Autowired
	private VehicleReservationService vehResService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<VehicleReservation>> getAll() {

		Collection<VehicleReservation> vehRess = vehResService.findAll();

		return new ResponseEntity<>(vehRess, HttpStatus.OK);
	}

	/*
	 * @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<VehicleReservation>
	 * create(@RequestBody VehicleReservation vehRes){
	 * 
	 * VehicleReservation vehicleReservation = null;
	 * 
	 * try { vehicleReservation = vehResService.save(vehRes); } catch (Exception e)
	 * { e.printStackTrace(); }
	 * 
	 * if(vehicleReservation == null) { return new
	 * ResponseEntity<VehicleReservation>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * return new ResponseEntity<VehicleReservation>(vehicleReservation,
	 * HttpStatus.OK); }
	 */

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<VehicleReservation>> findByUser(@PathVariable("userId") Long userId) {

		Collection<VehicleReservation> vehRess = vehResService.findByUser(userId);
		return new ResponseEntity<Collection<VehicleReservation>>(vehRess, HttpStatus.OK);
	}

	// @PreAuthorize("hasRole('USER')")
	@DeleteMapping(value = "/{vrId}")
	public ResponseEntity<?> deleteVehicleReservation(@PathVariable("vrId") Long vrId) {

		// RoomReservation r = roomResService.findOne(rrId);
		VehicleReservation vr = vehResService.findOne(vrId).get();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 2);
		if (cal.getTime().after(vr.getCheckInDate())) {

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		} else {

			vehResService.delete(vrId);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

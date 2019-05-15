package rs.travel.bookingWithEase.controller;

import java.util.Collection;

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

import rs.travel.bookingWithEase.dto.VehicleReservationDTO;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.model.VehicleReservation;
import rs.travel.bookingWithEase.service.UserService;
import rs.travel.bookingWithEase.service.VehicleReservationService;
import rs.travel.bookingWithEase.service.VehicleService;

@Controller
@RequestMapping(value = "/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private VehicleReservationService vehReservationService;
	
	@Autowired
	private UserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Vehicle>> getAll() {

		Collection<Vehicle> vehicles = vehicleService.findAll();

		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMINRAC')")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {

		if (vehicle.getRegistrationNumber().trim().equals("") || vehicle.getRegistrationNumber() == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (vehicleService.findByRegNumber(vehicle.getRegistrationNumber()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Vehicle veh = null;

		try {
			veh = vehicleService.save(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (veh == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(veh, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMINRAC')")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle) {

		Vehicle veh = null;
		try {
			veh = vehicleService.save(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (veh == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(veh, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> findOne(@PathVariable("id") Long id) {
		Vehicle veh = vehicleService.findOne(id);

		return new ResponseEntity<>(veh, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMINRAC')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id) {

		Vehicle vehicle = vehicleService.findOne(id);
		if (vehicle != null) {
			if (vehicle.getVehicleReservations().size() > 0) {
				return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
			}
			vehicleService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{id}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<VehicleReservation>> getMyReservations(@PathVariable("id") Long id) {
		Vehicle vehicle = vehicleService.findOne(id);

		return new ResponseEntity<>(vehicle.getVehicleReservations(),
				HttpStatus.OK);
	}

	@PostMapping(value = "/{id}/reservations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehicleReservation> addReservation(@PathVariable("id") Long id,
			@RequestBody VehicleReservationDTO vehResdto) {
		
		VehicleReservation vehRes = new VehicleReservation();
		vehRes.setCheckInDate(vehResdto.getCheckInDate());
		vehRes.setCheckOutDate(vehRes.getCheckOutDate());
		vehRes.setTotalPrice(vehResdto.getTotalPrice());
		
		VehicleReservation vehicleReservation = null;
		Vehicle veh = null;
		RegisteredUser user = null;
		try {
			veh = vehicleService.findOne(id);
			vehRes.setVehicle(veh);
			user = (RegisteredUser) userService.findOne(vehResdto.getUser_id());
			vehRes.setUser(user);
			vehicleReservation = vehReservationService.save(vehRes);
			veh.getVehicleReservations().add(vehicleReservation);
			user.getVehicleReservations().add(vehicleReservation);
			vehicleService.save(veh);
			userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (vehicleReservation == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(vehicleReservation, HttpStatus.OK);
	}
}

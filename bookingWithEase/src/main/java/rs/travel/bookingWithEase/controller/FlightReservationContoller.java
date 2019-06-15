package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.model.FlightInvite;
import rs.travel.bookingWithEase.model.FlightReservation;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.service.FlightInviteService;
import rs.travel.bookingWithEase.service.FlightReservationService;
import rs.travel.bookingWithEase.service.FlightService;
import rs.travel.bookingWithEase.service.UserService;

@RestController
@RequestMapping("/flightReservation")
public class FlightReservationContoller {

	@Autowired
	private FlightReservationService flightReservationService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FlightInviteService flightInviteService;
	
	@GetMapping(value = "/new/{flightId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FlightReservation> create(@PathVariable("flightId") Long flightId, @PathVariable("userId") Long userId) {

		Flight flight = flightService.findOne(flightId);
		User regUser = userService.findOne(userId);
		
		FlightReservation flightReservation = flightReservationService.findByFUserAndFlightId((RegisteredUser) regUser, flightId);
		
		if(flightReservation != null) {
			return new ResponseEntity<FlightReservation>(flightReservation, HttpStatus.OK);
		}
		
		flightReservation = new FlightReservation();
		flightReservation.setFUser((RegisteredUser) regUser);
		flightReservation.setFlight(flight);		
		flightReservationService.save(flightReservation);
		
		FlightInvite flightInvite = new FlightInvite();
		
		flightInvite.setAccepted(true);
		flightInvite.setExpirationDate(new Date());
		flightInvite.setFriendEmail(regUser.getEmail());
		flightInvite.setFirstname(regUser.getFirstName());
		flightInvite.setLastname(regUser.getLastName());		
		flightInvite.setPassport(regUser.getPassportNumber());
		flightInvite.setReservation(flightReservation);
		flightInvite.setSeatId((long) 0);		
		flightInviteService.save(flightInvite);
		
		return new ResponseEntity<FlightReservation>(flightReservation, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<FlightReservation>> findByUser(@PathVariable("userId") Long userId) {

		Collection<FlightReservation> flRess = flightReservationService.findByUser(userId);
		return new ResponseEntity<Collection<FlightReservation>>(flRess, HttpStatus.OK);
	}

}

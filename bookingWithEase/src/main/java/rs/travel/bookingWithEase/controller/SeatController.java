package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.model.Seat;
import rs.travel.bookingWithEase.service.SeatService;

@RestController
@RequestMapping(value = "/seats")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@GetMapping(value = "/flight/{flightId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Seat>> getSeats(@PathVariable("flightId") Long flightId) {

		List<Seat> seats = seatService.findByFlightId(flightId);

		return new ResponseEntity<Collection<Seat>>(seats, HttpStatus.OK);
	}

}

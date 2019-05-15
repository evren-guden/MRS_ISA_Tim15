package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.dto.DefiningQrrDTO;
import rs.travel.bookingWithEase.model.QuickRoomReservation;
import rs.travel.bookingWithEase.service.QuickRoomReservationService;

@Controller
@RequestMapping(value = "hotels/{hotelId}/quickRoomReservations")
public class QuickRoomReservationController {

	@Autowired
	private QuickRoomReservationService quickRoomReservationService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<QuickRoomReservation>> getAll() {

		Collection<QuickRoomReservation> quickRoomRes = quickRoomReservationService.findAll();

		return new ResponseEntity<>(quickRoomRes, HttpStatus.OK);
	}

	// @PreAuthorize("hasRole('USER')")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<QuickRoomReservation>> create(@RequestBody DefiningQrrDTO roomResDTO) {
		List<QuickRoomReservation> reservations = quickRoomReservationService.dtoToReservations(roomResDTO);

		for (QuickRoomReservation qrr : reservations) {
			try {
				quickRoomReservationService.save(qrr);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	
		return new ResponseEntity<>(quickRoomReservationService.findAll(), HttpStatus.OK);
	}
	
	// @PreAuthorize("hasRole('USER')")
	@DeleteMapping(value = "/{qrrId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<QuickRoomReservation>> deleteUserRoomReservation(@PathVariable("hotelId") Long hotelId, @PathVariable("qrrId") Long qrrId) {

		quickRoomReservationService.delete(qrrId);
				
		return new ResponseEntity<>(quickRoomReservationService.findAll(), HttpStatus.OK);
	}

}

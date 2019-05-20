package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.List;

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

import rs.travel.bookingWithEase.dto.DefiningQrrDTO;
import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.dto.RoomSearchDTO;
import rs.travel.bookingWithEase.model.QuickRoomReservation;
import rs.travel.bookingWithEase.model.Room;
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
	
	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Collection<QuickRoomReservation>> searchRooms(@RequestBody RoomSearchDTO roomSearchDTO) {
		System.out.println("\n\n\n" + roomSearchDTO + "\n\n\n");
		if (roomSearchDTO.getMaxPrice() == 0)
			roomSearchDTO.setMaxPrice(999999);

		System.out.println("\n\n\n" + roomSearchDTO + "\n\n\n");

		Collection<QuickRoomReservation> services = quickRoomReservationService.search(roomSearchDTO);
		return new ResponseEntity<>(services, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping(value = "/{qrrId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickRoomReservation> bookQrr(@RequestBody Long userId,
			@PathVariable("qrrId") Long qrrId) {
		
		try {
			quickRoomReservationService.bookQrr(qrrId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(quickRoomReservationService.findOne(qrrId), HttpStatus.OK);

	}

}

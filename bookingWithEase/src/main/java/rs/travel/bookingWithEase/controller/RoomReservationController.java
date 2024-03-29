package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.dto.RoomReservationDTO;
import rs.travel.bookingWithEase.model.RoomReservation;
import rs.travel.bookingWithEase.service.RoomReservationService;

@Controller
@RequestMapping(value="hotels/{hotelId}/roomReservations")
public class RoomReservationController {
	
	@Autowired
	private RoomReservationService roomResService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RoomReservation>> getAll(){
		
		Collection<RoomReservation> roomRes = roomResService.findAll();
		
		return new ResponseEntity<>(roomRes, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoomReservation> create(@RequestBody RoomReservationDTO roomResDTO){
		RoomReservation roomRes = roomResService.dtoToReservation(roomResDTO);
		RoomReservation roomReservation = null;
		
		try {
			roomReservation = roomResService.save(roomRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(roomReservation == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(roomReservation, HttpStatus.OK);
	}
	
}

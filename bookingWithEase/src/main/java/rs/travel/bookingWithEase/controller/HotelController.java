package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.service.CompanyService;
import rs.travel.bookingWithEase.service.HotelService;
import rs.travel.bookingWithEase.service.RoomService;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CompanyService companyService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Hotel> getAll() throws JsonProcessingException {
		return hotelService.findAll();
	}
	
	@GetMapping(value = "/{hotelId}/rooms",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Room> getRooms() 
	{
		return roomService.findAll();
	}
	
	@PostMapping(value = "/{hotelId}/rooms",
			     consumes = MediaType.APPLICATION_JSON_VALUE,
			     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> addRoom(@RequestBody RoomDTO roomDto)
	{
		Room newRoom = roomService.dtoToRoom(roomDto);
		try {
			roomService.save(newRoom);
			System.out.println("Hotel id: " + newRoom.getHotel().getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Room>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Room>(newRoom, HttpStatus.OK);

	}
	
	@PutMapping(value = "/{id}",
			    consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> update(@RequestBody CompanyDTO companyDto)
	{	
		Company company = companyService.dtoToCompany(companyDto);
		
		Hotel updated = hotelService.findById(companyDto.getId());
		
		if (updated == null)
		{
			return new ResponseEntity<Hotel>(HttpStatus.BAD_REQUEST);
		}
		
		Hotel hotel = null;
		try {
			hotel = hotelService.save(new Hotel(company));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(hotel == null) {
			return new ResponseEntity<Hotel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
}

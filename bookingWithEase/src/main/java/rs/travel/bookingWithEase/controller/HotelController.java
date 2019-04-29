package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.HotelSpecialOffer;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.service.CompanyService;
import rs.travel.bookingWithEase.service.HotelService;
import rs.travel.bookingWithEase.service.HotelSpecialOfferService;
import rs.travel.bookingWithEase.service.RoomService;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private HotelSpecialOfferService specialOfferService;

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@GetMapping("/secured/all")
	public String securedHello() {
		return "Secured Hello";
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Hotel> getAll() throws JsonProcessingException {

		return hotelService.findAll();
	}

	@GetMapping(value = "/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hotel findById(@PathVariable("hotelId") Long id) throws JsonProcessingException {

		return hotelService.findById(id);
	}

	@GetMapping(value = "/{hotelId}/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Room> getRooms(@PathVariable("hotelId") Long id) {
		return roomService.findByHotelId(id);
	}

	@DeleteMapping(value = "/{hotelId}/rooms/{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Room> deleteRoom(@PathVariable("roomId") Long roomId, @PathVariable("hotelId") Long hotelId) {
		roomService.delete(roomId);

		return roomService.findByHotelId(hotelId);
	}

	@PutMapping(value = "/{hotelId}/rooms/{roomId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Room>> updateRoom(@RequestBody RoomDTO roomDto,
			@PathVariable("roomId") Long roomId, @PathVariable("hotelId") Long hotelId) {
		Room newRoom = roomService.dtoToRoom(roomDto);
		// newRoom.setHotel(hotelService.findById(hotelId));
		System.out.println("hotelId" + newRoom.getHotel().getId() + " roomId " + newRoom.getId());
		try {
			roomService.save(newRoom);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Collection<Room>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Collection<Room>>(roomService.findByHotelId(hotelId), HttpStatus.OK);

	}

	@PostMapping(value = "/{hotelId}/rooms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> addRoom(@RequestBody RoomDTO roomDto) {
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

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> update(@RequestBody CompanyDTO companyDto) {
		Company company = companyService.dtoToCompany(companyDto);

		Hotel updated = hotelService.findById(companyDto.getId());

		if (updated == null) {
			return new ResponseEntity<Hotel>(HttpStatus.BAD_REQUEST);
		}

		Hotel hotel = new Hotel(company);
		hotel.setStars(companyDto.getStars());
		try {
			hotel = hotelService.save(hotel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (hotel == null) {
			return new ResponseEntity<Hotel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Hotel>> search(@RequestBody CompanyDTO companyDTO) {
		Company company = companyService.dtoToCompany(companyDTO);
		Hotel hotel = new Hotel(company);
		Collection<Hotel> services = hotelService.search(hotel);
		return new ResponseEntity<Collection<Hotel>>(services, HttpStatus.OK);
	}

	// *************** SPECIAL OFFERS ************

	@GetMapping(value = "/{hotelId}/specialOffers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<HotelSpecialOffer> getSpecialOffers(@PathVariable("hotelId") Long id)
			throws JsonProcessingException {

		return hotelService.findById(id).getSpecialOffers();
	}

	@PostMapping(value = "/{hotelId}/specialOffers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelSpecialOffer> addSpecialOffer(@PathVariable("hotelId") Long id,
			@RequestBody HotelSpecialOffer hotelSpecialOffer) {

		HotelSpecialOffer newHSO = null;
		try {
			newHSO = specialOfferService.save(hotelSpecialOffer);
			System.out.println("\n\nHotel service id: " + newHSO.getId());
			Hotel hotel = hotelService.findById(id);
			hotel.getSpecialOffers().add(newHSO);
			hotelService.save(hotel);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HotelSpecialOffer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HotelSpecialOffer>(newHSO, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{hotelId}/specialOffers/{specialOfferId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<HotelSpecialOffer>> deleteSpecialOffer(@PathVariable("hotelId") Long hotelId,
			@PathVariable("specialOfferId") Long specialOfferId) {
		specialOfferService.delete(specialOfferId);
		Hotel hotel = hotelService.findById(hotelId);
		hotel.getSpecialOffers().removeIf(h -> (h.getId() == specialOfferId));
		hotelService.save(hotel);
		return new ResponseEntity<Collection<HotelSpecialOffer>>(hotelService.findById(hotelId).getSpecialOffers(),
				HttpStatus.OK);
	}

	@PutMapping(value = "/{hotelId}/specialOffers/{specialOfferId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<HotelSpecialOffer>> updateSpecialOffer(@PathVariable("hotelId") Long hotelId,
			@PathVariable("specialOfferId") Long specialOfferId, @RequestBody HotelSpecialOffer hso) {

		try {
			specialOfferService.save(hso);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Collection<HotelSpecialOffer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Collection<HotelSpecialOffer>>(hotelService.findById(hotelId).getSpecialOffers(),
				HttpStatus.OK);
	}
}

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.dto.HotelSearchDTO;
import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.dto.RoomSearchDTO;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.HotelServiceTypePrices;
import rs.travel.bookingWithEase.model.HotelSpecialOffer;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.service.CompanyService;
import rs.travel.bookingWithEase.service.HotelService;
import rs.travel.bookingWithEase.service.HotelServiceTypePricesService;
import rs.travel.bookingWithEase.service.HotelSpecialOfferService;
import rs.travel.bookingWithEase.service.RoomService;
import rs.travel.booking_with_ease.exceptions.EntityAlreadyExistsException;
import rs.travel.booking_with_ease.exceptions.EntityNotEditableException;

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
	private HotelServiceTypePricesService serviceTypePrices;

	@Autowired
	private HotelSpecialOfferService specialOfferService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Hotel>> getAll() throws JsonProcessingException {

		return new ResponseEntity<>(hotelService.findAll(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> findById(@PathVariable("hotelId") Long id) throws JsonProcessingException {

		return new ResponseEntity<>(hotelService.findById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> update(@RequestBody CompanyDTO companyDto) {
		Company company = companyService.dtoToCompany(companyDto);

		Hotel updated = hotelService.findById(companyDto.getId());

		if (updated == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Hotel hotel = new Hotel(company);
		hotel.setStars(companyDto.getStars());
		try {
			hotel = hotelService.save(hotel);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		if (hotel == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Hotel>> search(@RequestBody HotelSearchDTO hotelSearchDTO) {
		
		Collection<Hotel> services = hotelService.search(hotelSearchDTO);
		return new ResponseEntity<>(services, HttpStatus.OK);

	}

	// ******************* ROOMS *****************

	
	@GetMapping(value = "/{hotelId}/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Room> getRooms(@PathVariable("hotelId") Long id) {
		return roomService.findByHotelId(id);
	}

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@PostMapping(value = "/{hotelId}/rooms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> addRoom(@RequestBody RoomDTO roomDto) throws EntityAlreadyExistsException {
		Room newRoom = roomService.dtoToRoom(roomDto);

		roomService.addNewRoom(newRoom);
		
	//	HttpHeaders responseHeaders = new HttpHeaders();
	//	responseHeaders.set("message", message);
	
		return new ResponseEntity<>(newRoom, HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@DeleteMapping(value = "/{hotelId}/rooms/{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Room>> deleteRoom(@PathVariable("roomId") Long roomId, @PathVariable("hotelId") Long hotelId) throws EntityNotEditableException {
		roomService.delete(roomId);

		return new ResponseEntity<>(roomService.findByHotelId(hotelId), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@PutMapping(value = "/{hotelId}/rooms/{roomId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Room>> updateRoom(@RequestBody RoomDTO roomDto,
			@PathVariable("roomId") Long roomId, @PathVariable("hotelId") Long hotelId) throws EntityAlreadyExistsException, EntityNotEditableException {
		Room newRoom = roomService.dtoToRoom(roomDto);
		// newRoom.setHotel(hotelService.findById(hotelId));
		System.out.println("hotelId" + newRoom.getHotel().getId() + " roomId " + newRoom.getId());
	
		roomService.updateRoom(newRoom);
		
		return new ResponseEntity<>(roomService.findByHotelId(hotelId), HttpStatus.OK);

	}
	
	@PostMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Collection<RoomDTO>> searchRooms(@RequestBody RoomSearchDTO roomSearchDTO) {
		System.out.println("\n\n\n" + roomSearchDTO + "\n\n\n");
		if (roomSearchDTO.getMaxPrice() == 0)
			roomSearchDTO.setMaxPrice(999999);

		System.out.println("\n\n\n" + roomSearchDTO + "\n\n\n");

		Collection<RoomDTO> services = roomService.search(roomSearchDTO);
		return new ResponseEntity<>(services, HttpStatus.OK);
	}


	// *************** SPECIAL OFFERS ************

	@GetMapping(value = "/{hotelId}/specialOffers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<HotelSpecialOffer>> getSpecialOffers(@PathVariable("hotelId") Long id)
			throws JsonProcessingException {

		return new ResponseEntity<>(hotelService.findById(id).getSpecialOffers(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@PostMapping(value = "/{hotelId}/specialOffers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelSpecialOffer> addSpecialOffer(@PathVariable("hotelId") Long id,
			@RequestBody HotelSpecialOffer hotelSpecialOffer) {

		HotelSpecialOffer newHSO = null;
		try {
			newHSO = specialOfferService.save(hotelSpecialOffer);
		
			Hotel hotel = hotelService.findById(id);
			hotel.getSpecialOffers().add(newHSO);
			hotelService.save(hotel);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(newHSO, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@DeleteMapping(value = "/{hotelId}/specialOffers/{specialOfferId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<HotelSpecialOffer>> deleteSpecialOffer(@PathVariable("hotelId") Long hotelId,
			@PathVariable("specialOfferId") Long specialOfferId) {
		specialOfferService.delete(specialOfferId);
		Hotel hotel = hotelService.findById(hotelId);
		hotel.getSpecialOffers().removeIf(h -> (h.getId().equals(specialOfferId)));
		hotelService.save(hotel);
		return new ResponseEntity<>(hotelService.findById(hotelId).getSpecialOffers(),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@PutMapping(value = "/{hotelId}/specialOffers/{specialOfferId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<HotelSpecialOffer>> updateSpecialOffer(@PathVariable("hotelId") Long hotelId,
			@PathVariable("specialOfferId") Long specialOfferId, @RequestBody HotelSpecialOffer hso) {

		try {
			specialOfferService.save(hso);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(hotelService.findById(hotelId).getSpecialOffers(),
				HttpStatus.OK);
	}

	// ********* SERVICE TYPE PRICES ***********

	@PreAuthorize("hasRole('ADMINHOTEL')")
	@PutMapping(value = "/{hotelId}/serviceTypePrices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> getServiceTypePrices(@PathVariable("hotelId") Long id,
			@RequestBody HotelServiceTypePrices hstp) throws JsonProcessingException {

		Hotel hotel = hotelService.findById(id);
		System.out.println("\n\n\n" + hstp);
		hotel.setServiceTypePrices(hstp);
		hotelService.save(hotel);
		serviceTypePrices.save(hstp);

		return new ResponseEntity<>(hotelService.findById(id), HttpStatus.OK);
	}

}

package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.service.CompanyService;
import rs.travel.bookingWithEase.service.HotelService;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private CompanyService companyService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Hotel> getAll() throws JsonProcessingException {
		return hotelService.findAll();
	}
	
	@PutMapping(value = "/{id}",
			    consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> update(@RequestBody CompanyDTO companyDto)
	{	
		Company company = companyService.dtoToCompany(companyDto);
		
		Optional<Hotel> updated = hotelService.getById(companyDto.getId());
		
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

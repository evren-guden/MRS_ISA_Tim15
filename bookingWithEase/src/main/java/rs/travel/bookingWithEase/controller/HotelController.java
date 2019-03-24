package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.service.HotelService;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Hotel> getAll() throws JsonProcessingException {
		return hotelService.findAll();
	}

}

package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.service.RACService;

@RestController
@RequestMapping(value = "/rentacars")
public class RentACarController {

	@Autowired
	private RACService rentACarService;

	@GetMapping("/all")
	public String hello() {
		return "Hello Youtube";
	}

	@PreAuthorize("hasRole('ADMINRAC')")
	@GetMapping("/secured/all")
	public String securedHello() {
		return "Secured Hello";
	}

	@GetMapping("/secured/alternate")
	    public String alternate() {
	        return "alternate";
	    }

	@PreAuthorize("hasRole('ADMINRAC')")
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RentACar>> getAll() {

		Collection<RentACar> racs = rentACarService.findAll();

		return new ResponseEntity<Collection<RentACar>>(racs, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RentACar>> search(@RequestBody RentACarDTO rentACar) {
		Collection<RentACar> services = rentACarService.search(rentACar);
		return new ResponseEntity<Collection<RentACar>>(services, HttpStatus.OK);
	}
}

package rs.travel.bookingWithEase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.service.RACService;

@RestController
@RequestMapping(value = "/rentacars")
public class RentACarController {
	
	@Autowired
	private RACService rentACarService;
	
    	
}

package rs.travel.bookingWithEase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.service.AirlineService;
import rs.travel.bookingWithEase.service.CompanyService;
import rs.travel.bookingWithEase.service.HotelService;
import rs.travel.bookingWithEase.service.RACService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private AirlineService airlineService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RACService rentacarService;
	
	@Autowired 
	private CompanyService companyService;

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Company add(@RequestBody CompanyDTO companyDto) throws Exception {
		
		Company company = companyService.dtoToCompany(companyDto);
		
		switch (companyDto.getCmpType()) {
		case "airline":
			airlineService.save(new Airline(company));
			System.out.println("Airline added");
			break;
		case "hotel":
			hotelService.save(new Hotel(company));
			System.out.println("Hotel added");
			break;
		case "rentacar":
			rentacarService.save(new RentACar(company));
			System.out.println("Rentacar added");
			break;
		}
		return company;
	}


}

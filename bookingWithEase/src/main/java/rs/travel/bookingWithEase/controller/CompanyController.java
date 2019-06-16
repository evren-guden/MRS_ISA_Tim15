package rs.travel.bookingWithEase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.model.Admin;
import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.service.AirlineService;
import rs.travel.bookingWithEase.service.CompanyService;
import rs.travel.bookingWithEase.service.HotelService;
import rs.travel.bookingWithEase.service.RACService;
import rs.travel.bookingWithEase.service.UserService;

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
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Company add(@RequestBody CompanyDTO companyDto) throws Exception {

		Company company = companyService.dtoToCompany(companyDto);
		Company savedCompany = null;

		switch (companyDto.getCmpType()) {
		case "airline":
			savedCompany = airlineService.save(new Airline(company));

			break;
		case "hotel":
			savedCompany = hotelService.save(new Hotel(company));
			
			break;
		case "rentacar":
			savedCompany = rentacarService.save(new RentACar(company));

			break;
		}
		
		for(Admin a : company.getAdmins()) {
			a.setCompany(savedCompany);
			userService.update(a);
		}
				
		return company;
	}

}

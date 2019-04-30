package rs.travel.bookingWithEase.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.Branch;
import rs.travel.bookingWithEase.model.RACSpecialOffer;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.service.BranchService;
import rs.travel.bookingWithEase.service.RACService;
import rs.travel.bookingWithEase.service.RACSpecialOfferService;

@RestController
@RequestMapping(value = "/rentacars")
public class RentACarController {

	@Autowired
	private RACService rentACarService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired 
	private RACSpecialOfferService racOfferService;

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

	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RentACar>> getAll() {

		Collection<RentACar> racs = rentACarService.findAll();

		return new ResponseEntity<Collection<RentACar>>(racs, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}/branchs",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Branch>> getMyBranchs(@PathVariable("id") Long id) {
		
		RentACar rac = rentACarService.findOne(id);
		
		return new ResponseEntity<Collection<Branch>>(rac.getBranches(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMINRAC')")
	@PostMapping(value="/{id}/branchs",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> addBranch(@PathVariable("id") Long id, @RequestBody Branch branch) {

		if(branch.getName().trim().equals("") || branch.getName()==null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		RentACar rac = rentACarService.findOne(id);
		rac.addBranch(branch);
		branch.setRac(rac);
		Branch br = branchService.save(branch);
		rentACarService.save(rac);
		
		return new ResponseEntity<Branch>(br, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Vehicle>> getMyVehicles(@PathVariable("id") Long id){
		RentACar rac = rentACarService.findOne(id);
		
		List<Vehicle> vehs = new ArrayList<Vehicle>();
		
		for (Branch br : rac.getBranches()) {
			for (Vehicle vehicle : br.getVehicles()) {
				vehs.add(vehicle);
			}
		}
		
		return new ResponseEntity<Collection<Vehicle>>(vehs, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RentACar>> search(@RequestBody RentACarDTO rentACar) {
		Collection<RentACar> services = rentACarService.search(rentACar);
		return new ResponseEntity<Collection<RentACar>>(services, HttpStatus.OK);
	}
	
	/*******************************************   Special offer   */
	
	@GetMapping(value = "/{id}/specialOffers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<RACSpecialOffer> getRacsOffers(@PathVariable("id") Long id){
		RentACar rac = rentACarService.findOne(id);
		
		return rac.getSpecialOffers();
	}
	
	@PostMapping(value = "/{id}/specialOffers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RACSpecialOffer> addSpecialOffer(@PathVariable("id") Long id, @RequestBody RACSpecialOffer offer){
		
		if(offer.getName().trim().equals("") || offer.getName() == null || offer.getPrice() == null || offer.getPrice().doubleValue()<0) {
			return new ResponseEntity<RACSpecialOffer>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		RACSpecialOffer so = null;
		
		try {
			so = racOfferService.save(offer);
			RentACar rac = rentACarService.findOne(id);
			rac.getSpecialOffers().add(so);
			so.setRacservice(rac);
			rentACarService.save(rac);
		} catch (Exception e) {
			return new ResponseEntity<RACSpecialOffer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RACSpecialOffer>(so, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}/specialOffers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RACSpecialOffer> updateOffer(@PathVariable("id") Long id, @RequestBody RACSpecialOffer offer){
		RACSpecialOffer so = null;
		try {
			so = racOfferService.save(offer);
		} catch (Exception e) {
			return new ResponseEntity<RACSpecialOffer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<RACSpecialOffer>(so, HttpStatus.OK);
	}
	
}

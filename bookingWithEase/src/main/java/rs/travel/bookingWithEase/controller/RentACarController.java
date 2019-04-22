package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.Branch;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.service.BranchService;
import rs.travel.bookingWithEase.service.RACService;

@RestController
@RequestMapping(value = "/rentacars")
public class RentACarController {

	@Autowired
	private RACService rentACarService;
	
	@Autowired
	private BranchService branchService;

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

	//@PreAuthorize("hasRole('ADMINRAC')")
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RentACar>> getAll() {

		Collection<RentACar> racs = rentACarService.findAll();

		return new ResponseEntity<Collection<RentACar>>(racs, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}/branchs",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Branch>> getMyBranchs(@PathVariable("id") Long id) {
		
		Optional<RentACar> rac = rentACarService.findOne(id);
		
		return new ResponseEntity<Collection<Branch>>(rac.get().getBranches(), HttpStatus.OK);
	}
	
	@PostMapping(value="/{id}/branchs",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> addBranch(@PathVariable("id") Long id, @RequestBody Branch branch) {

		Optional<RentACar> rac = rentACarService.findOne(id);
		rac.get().addBranch(branch);
		branch.setRac(rac.get());
		Branch br = branchService.save(branch);
		rentACarService.save(rac.get());
		
		return new ResponseEntity<Branch>(br, HttpStatus.OK);
	}
	
	/*@PutMapping(value="/{id}/branchs",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> updateBranch(Branch branch) {

		Branch br = branchService.save(branch);
		return new ResponseEntity<Branch>(br, HttpStatus.OK);
	}*/

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RentACar>> search(@RequestBody RentACarDTO rentACar) {
		Collection<RentACar> services = rentACarService.search(rentACar);
		return new ResponseEntity<Collection<RentACar>>(services, HttpStatus.OK);
	}
}

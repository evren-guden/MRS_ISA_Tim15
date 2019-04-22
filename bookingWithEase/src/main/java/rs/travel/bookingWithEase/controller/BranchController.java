package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.model.Branch;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.service.BranchService;
import rs.travel.bookingWithEase.service.VehicleService;

@Controller
@RequestMapping(value = "/branchs")
public class BranchController {
	@Autowired
	private BranchService branchService;

	@Autowired
	private VehicleService vehicleService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Branch>> getAll() {

		Collection<Branch> branches = branchService.findAll();

		return new ResponseEntity<Collection<Branch>>(branches, HttpStatus.OK);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> update(@RequestBody Branch br) {

		Optional<Branch> branch = branchService.findOne(br.getId());

		if (branch == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		branch.get().setName(br.getName());
		branch.get().setAddress(br.getAddress());

		Branch branch2 = branchService.save(branch.get());
		return new ResponseEntity<>(branch2, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branch> findOne(@PathVariable("id") Long id) {

		Optional<Branch> br = branchService.findOne(id);

		return new ResponseEntity<Branch>(br.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Vehicle>> getMyVehicles(@PathVariable("id") Long id){
		Optional<Branch> branch = branchService.findOne(id);
		
		return new ResponseEntity<Collection<Vehicle>>(branch.get().getVehicles(), HttpStatus.OK);
	}

	@PostMapping(value = "/{id}/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> addVehicle(@PathVariable("id") Long id, @RequestBody Vehicle vehicle) {

		Optional<Branch> branch = branchService.findOne(id);
		branch.get().addVehicle(vehicle);
		vehicle.setBranch(branch.get());
		Vehicle veh = vehicleService.save(vehicle);
		branchService.save(branch.get());

		return new ResponseEntity<Vehicle>(veh, HttpStatus.OK);
	}

	@PostMapping(value = "/delete/{id}")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") Long id) {

		System.out.println("delete " + id);
		branchService.delete(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}

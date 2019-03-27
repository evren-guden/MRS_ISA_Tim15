package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.model.Branch;
import rs.travel.bookingWithEase.service.BranchService;

@Controller
@RequestMapping(value="/branches")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Branch>> getAll(){
		
		Collection<Branch> branches = branchService.findAll();

		return new ResponseEntity<Collection<Branch>>(branches, HttpStatus.OK);
	}
}

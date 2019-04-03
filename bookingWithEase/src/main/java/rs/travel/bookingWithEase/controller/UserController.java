package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.AdminUserDTO;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<User> findAll()
	{
		return userService.findAll();
	}
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody AdminUserDTO adminUserDto) throws Exception
	{
		User newUser = userService.dtoToUser(adminUserDto);
		if (userService.save(newUser))
		{	
			System.out.println("created");
			return new ResponseEntity<String>(HttpStatus.OK);			
		}
		else
		{	
			System.out.println("conflict");
			return new ResponseEntity<String>("Username is already taken",HttpStatus.CONFLICT);
		}
		
	}
	
}

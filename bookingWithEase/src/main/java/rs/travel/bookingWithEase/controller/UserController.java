package rs.travel.bookingWithEase.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.AdminUserDTO;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.RoomReservation;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.security.TokenUtils;
import rs.travel.bookingWithEase.service.UserService;
import rs.travel.bookingWithEase.service.RoomReservationService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoomReservationService roomResService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<User> findAll() {
		return userService.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody AdminUserDTO adminUserDto) throws Exception {
		adminUserDto.setType("hotel");
		User newUser = userService.dtoToUser(adminUserDto);

		if (userService.save(newUser)) {
			System.out.println("\nnew user created\n");
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			System.out.println("conflict");
			return new ResponseEntity<String>("Username is already taken", HttpStatus.CONFLICT);
		}

	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public
	 * ResponseEntity<User> login(@RequestBody JwtAuthenticationRequest
	 * authenticationRequest, HttpServletResponse response) {
	 * 
	 * final Authentication authentication = authenticationManager .authenticate(new
	 * UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
	 * authenticationRequest.getPassword()));
	 * 
	 * // Ubaci username + password u kontext
	 * SecurityContextHolder.getContext().setAuthentication(authentication); //
	 * Kreiraj token User user = (User) authentication.getPrincipal();
	 * 
	 * // Vrati token kao odgovor na uspesno autentifikaciju return
	 * ResponseEntity.ok(user); }
	 */

	@PreAuthorize("hasRole('ADMIN') or hasRole('ADMINRAC') or hasRole('ADMINHOTEL') or hasRole('ADMINAIRLINE') or hasRole('USER')")
	@GetMapping(value = "/myprofile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> myProfile(@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null) {
			return null;
		}
		System.out.println("MY profile");
		String token = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}

		String username = tokenUtils.getUsernameFromToken(token);

		User user = userService.findByUsername(username);

		return ResponseEntity.ok(user);
	}

	// **************** RESERVATIONS *************
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping(value="/{userId}/roomReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RoomReservation>> getUserRoomReservations(@PathVariable("userId") Long userId)
	{	
		RegisteredUser u = (RegisteredUser)userService.findOne(userId);
		return new ResponseEntity<Collection<RoomReservation>>(roomResService.findByUser(u),HttpStatus.OK);
	}

}

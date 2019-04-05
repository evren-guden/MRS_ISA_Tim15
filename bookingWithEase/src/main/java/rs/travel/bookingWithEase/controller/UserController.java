package rs.travel.bookingWithEase.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.AdminUserDTO;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.security.auth.JwtAuthenticationRequest;
import rs.travel.bookingWithEase.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@RequestMapping("/whoami")
	@PreAuthorize("hasRole('USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}

	/*
	 * @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) public
	 * Collection<User> findAll() { return userService.findAll(); }
	 */

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody AdminUserDTO adminUserDto) throws Exception {
		User newUser = userService.dtoToUser(adminUserDto);
		if (userService.save(newUser)) {
			System.out.println("created");
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			System.out.println("conflict");
			return new ResponseEntity<String>("Username is already taken", HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Kreiraj token
		User user = (User) authentication.getPrincipal();


		// Vrati token kao odgovor na uspesno autentifikaciju
		return ResponseEntity.ok(user);
	}

}

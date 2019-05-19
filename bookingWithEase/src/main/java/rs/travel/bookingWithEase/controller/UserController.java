package rs.travel.bookingWithEase.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.travel.bookingWithEase.dto.AccountDTO;
import rs.travel.bookingWithEase.dto.AdminUserDTO;
import rs.travel.bookingWithEase.model.Authority;
import rs.travel.bookingWithEase.model.ConfirmationToken;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.RoomReservation;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.security.TokenUtils;
import rs.travel.bookingWithEase.service.AuthorityService;
import rs.travel.bookingWithEase.service.ConfTokenService;
import rs.travel.bookingWithEase.service.EmailSenderService;
import rs.travel.bookingWithEase.service.RoomReservationService;
import rs.travel.bookingWithEase.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoomReservationService roomResService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private ConfTokenService confTokenService;

	@Autowired
	private EmailSenderService emailService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityService authService;

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
			return new ResponseEntity<>("Username is already taken", HttpStatus.CONFLICT);
		}

	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> update(@RequestBody AccountDTO dto) throws Exception {
		
		User user = userService.findByEmail(dto.getEmail());

		user.setFirstName(dto.getName());
		user.setLastName(dto.getLastName());
		user.setCity(dto.getCity());
		user.setTelephoneNumber(dto.getPhoneNumber());
		
		User upUser = userService.update(user);
		
		return new ResponseEntity<>(upUser, HttpStatus.OK);
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

	// @PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/{userId}/roomReservations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RoomReservation>> getUserRoomReservations(@PathVariable("userId") Long userId) {
		RegisteredUser u = (RegisteredUser) userService.findOne(userId);
		return new ResponseEntity<>(roomResService.findByUser(u), HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('USER')")
	@DeleteMapping(value = "/{userId}/roomReservations/{rrId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RoomReservation>> deleteUserRoomReservation(@PathVariable("userId") Long userId,
			@PathVariable("rrId") Long rrId) {
		RegisteredUser u = (RegisteredUser) userService.findOne(userId);
		RoomReservation r = roomResService.findOne(rrId);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 2);
		if (cal.getTime().after(r.getCheckInDate())) {

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		} else {

			roomResService.cancelReservation(rrId);
		}
		return new ResponseEntity<>(roomResService.findByUser(u), HttpStatus.OK);
	}

	// registration

	@PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@RequestBody AccountDTO account) throws MessagingException {

		if(!account.getPassword().equals(account.getConfPassword())) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		User existingUser = userService.findByEmail(account.getEmail());
        if(existingUser != null) {
        	return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
		
        User user = new User(account);
        user.setPassword(passwordEncoder.encode(account.getPassword()));
        Authority a = authService.findByName("ROLE_USER");
        ArrayList<Authority> alist = new ArrayList<Authority>();
        alist.add(a);
        user.setAuthorities(alist);
        userService.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confTokenService.save(confirmationToken);
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom(env.getProperty("spring.mail.username"));
        mailMessage.setText("To confirm your account, please click here : "
        +"http://localhost:8080/users/confirm-account?token="+confirmationToken.getConfirmationToken());
        
        emailService.sendEmail(mailMessage);
        
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	 @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken)
	    {
	        ConfirmationToken token = confTokenService.findByConfirmationToken(confirmationToken);

	        if(token != null)
	        {
	            User user = userService.findByEmail(token.getUser().getEmail());
	            user.setEnabled(true);
	            Authority a = authService.findByName("ROLE_USER");
	            ArrayList<Authority> alist = new ArrayList<Authority>();
	            alist.add(a);
	            user.setAuthorities(alist);
	            userService.update(user);
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	 

	        return new ResponseEntity<>("Your account is enabled!", HttpStatus.UNPROCESSABLE_ENTITY);
	    }

}

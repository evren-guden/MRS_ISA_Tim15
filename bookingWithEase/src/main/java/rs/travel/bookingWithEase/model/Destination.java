package rs.travel.bookingWithEase.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;
@Component
@Entity

public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nameAerodroms")
	private String name;

	@Column(name = "address")
	private String address;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Airline airline;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Flight flight;

	@OneToOne(mappedBy = "startDestination")
	Flight startDestination;

	@OneToOne(mappedBy = "endDestination")
	Flight finalDestination;
	
	
	/*@OneToOne(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name="location")
	private Location location;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airline")
	private Airline airline;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Flight getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(Flight startDestination) {
		this.startDestination = startDestination;
	}

	public Flight getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(Flight finalDestination) {
		this.finalDestination = finalDestination;
	}

	public Destination(Long id, String name, String address, Airline airline, Flight flight,
			Flight startDestination, Flight finalDestination) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.airline = airline;
		this.flight = flight;
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
	}

	public Destination() {
		super();
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
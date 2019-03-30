package rs.travel.bookingWithEase.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;
@Component
@Entity

public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAerodromes;

	@Column(name = "nameAerodroms")
	private String nameAerodroms;

	@Column(name = "address")
	private String address;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Airline airline;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Flight flight;

	public Destination() {
		super();
	}

	public Destination(Long idAerodromes, String nameAerodroms, String address, Airline airline, Flight flight) {
		super();
		this.idAerodromes = idAerodromes;
		this.nameAerodroms = nameAerodroms;
		this.address = address;
		this.airline = airline;
		this.flight = flight;
	}
	
	
	
	public Destination(Destination d)
	{
		
	}
	
	
	

	public Long getIdAerodromes() {
		return idAerodromes;
	}

	public void setIdAerodromes(Long idAerodromes) {
		this.idAerodromes = idAerodromes;
	}

	public String getNameAerodroms() {
		return nameAerodroms;
	}

	public void setNameAerodroms(String nameAerodroms) {
		this.nameAerodroms = nameAerodroms;
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
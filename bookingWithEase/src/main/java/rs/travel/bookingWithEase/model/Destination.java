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

}
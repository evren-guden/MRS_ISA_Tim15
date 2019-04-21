package rs.travel.bookingWithEase.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rs.travel.bookingWithEase.dto.AirlineDTO;

@Component
@Entity
@Table(name = "Airline")
public class Airline extends Company {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight> flights = new HashSet<Flight>();
	
	
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Destination> destinations = new HashSet<Destination>();
	
	
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Airplane> airplanes = new HashSet<Airplane>();

	
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AirlineCustomerService> airlineCustomerServices = new HashSet<AirlineCustomerService>();

	

	public Airline() {
		super();
	}

	public Airline(long id, String name, String address, String description, double rating) {
		super(id, name, address, description, rating);

	}
	
	public Airline(Company company)
	{
		this.id = company.id;
		this.name = company.name;
		this.address = company.address;
		this.description = company.description;
		this.rating = company.rating;
	}

	@Override
	public String toString() {
		return "Airline [flights=" + flights + ", destinations=" + destinations + ", airplanes=" + airplanes
				+ ", airlineCustomerServices=" + airlineCustomerServices + "]";
	}
	
	public Airline(AirlineDTO airlineDTO) {
		this.name = airlineDTO.getAirlineNameRegister();
		this.address = airlineDTO.getAirlineAddressRegister();
		this.description = airlineDTO.getAirlinePromotionalDescription();
	}
	

}

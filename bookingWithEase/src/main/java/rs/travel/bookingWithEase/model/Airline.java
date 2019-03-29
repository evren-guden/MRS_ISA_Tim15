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

@Component
@Entity
@Table(name = "Airline")
public class Airline extends Company {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight> flights = new HashSet<Flight>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Destination> destinations = new HashSet<Destination>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Airplane> airplanes = new HashSet<Airplane>();

	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@Column(name = "airline_customer_service")
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
	
	

}

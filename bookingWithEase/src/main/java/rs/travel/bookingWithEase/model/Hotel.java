package rs.travel.bookingWithEase.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Hotel extends Company {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Room> rooms = new HashSet<Room>();

	public Hotel() {
		super();

	}

	public Hotel(long id, String name, String address, String description, double rating) {
		super(id, name, address, description, rating);

	}
	
	public Hotel(Company company)
	{
		this.id = company.id;
		this.name = company.name;
		this.address = company.address;
		this.description = company.description;
		this.rating = company.rating;
	}
	
	
	
}

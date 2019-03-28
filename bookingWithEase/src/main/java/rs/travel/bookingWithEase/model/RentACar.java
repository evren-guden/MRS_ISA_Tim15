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
public class RentACar extends Company {

	private static final long serialVersionUID = 1L;

	// private Location location;

	@OneToMany(mappedBy = "rac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Branch> branches = new HashSet<Branch>();

	@OneToMany(mappedBy = "rentacar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	
	public RentACar() {
	}

	public RentACar(long id, String name, String address, String description, double rating) {
		super(id, name, address, description, rating);

	}

	public RentACar(Company company) {
		this.id = company.id;
		this.name = company.name;
		this.address = company.address;
		this.description = company.description;
		this.rating = company.rating;
	}

	public Set<Branch> getBranches() {
		return branches;
	}

	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

}

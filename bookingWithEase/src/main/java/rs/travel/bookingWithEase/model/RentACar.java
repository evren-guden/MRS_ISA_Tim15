package rs.travel.bookingWithEase.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class RentACar extends Company {

	private static final long serialVersionUID = 1L;

	private Location location;
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	private Set<Branch> branches = new HashSet<Branch>();

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Set<Branch> getBranches() {
		return branches;
	}

	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

}

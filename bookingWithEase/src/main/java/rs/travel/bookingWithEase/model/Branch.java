package rs.travel.bookingWithEase.model;

import java.util.HashSet;
import java.util.Set;

public class Branch {
	
	private Long id;
	private Location location;
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	
	public Branch() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}

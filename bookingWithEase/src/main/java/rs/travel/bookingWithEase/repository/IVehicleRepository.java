package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Vehicle;

public interface IVehicleRepository {

	Collection<Vehicle> findAll();
	
	Vehicle find(Long id);
	
	Vehicle update(Vehicle vehicle);
	
	void initValues();
}

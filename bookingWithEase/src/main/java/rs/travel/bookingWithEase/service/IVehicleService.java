package rs.travel.bookingWithEase.service;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Vehicle;


public interface IVehicleService {
	Collection<Vehicle> findAll();
	
	Vehicle find(Long id);
	
	Vehicle update(Vehicle vehicle) throws Exception;
	void initValues();
}

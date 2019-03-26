package rs.travel.bookingWithEase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.repository.VehicleRepository;

@Service
public class VehicleService implements IVehicleService {

	@Autowired
	private VehicleRepository repository;

	@Override
	public Collection<Vehicle> findAll() {
		Collection<Vehicle> vehicles = repository.findAll();
		return vehicles;
	}

	@Override
	public Vehicle update(Vehicle vehicle) throws Exception {
		Vehicle findedVehicle = find(vehicle.getId());

		if (findedVehicle == null) {
			throw new Exception("Ne postoji vozilo servis sa tim identifikatorom.");
		}
		findedVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
		findedVehicle.setGear(vehicle.getGear());
		findedVehicle.setType(vehicle.getType());
		findedVehicle.setColor(vehicle.getColor());

		return repository.update(findedVehicle);
	}

	@Override
	public Vehicle find(Long id) {
		return repository.find(id);
	}

	@Override
	public void initValues() {
		repository.initValues();
		
	}

}

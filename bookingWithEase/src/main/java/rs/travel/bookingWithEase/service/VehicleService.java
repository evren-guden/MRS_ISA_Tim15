package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.repository.IVehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private IVehicleRepository vehicleRepository;

	public Optional<Vehicle> findOne(Long id) {
		return vehicleRepository.findById(id);
	}

	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public void delete(Long id) {
		vehicleRepository.deleteById(id);
	}

}

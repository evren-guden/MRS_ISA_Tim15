package rs.travel.bookingWithEase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.VehicleSearchDTO;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.repository.IVehicleRateRepository;
import rs.travel.bookingWithEase.repository.IVehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private IVehicleRepository vehicleRepository;
	
	@Autowired
	private IVehicleRateRepository vehRateRepository;

	public Vehicle findOne(Long id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		if (vehicle.isPresent()) {
			
			Double rating = vehRateRepository.findAverageByVehicle(id);

			vehicle.get().setRate(rating);
			
			return vehicle.get();
		}
		return null;
	}

	public List<Vehicle> findAll() {
		List<Vehicle> vehs = vehicleRepository.findAll();
		
		for (Vehicle vehicle : vehs) {
			vehicle.setRate(vehRateRepository.findAverageByVehicle(vehicle.getId()));
			System.out.println("Rating: " + vehicle.getRate());
		}
		return vehs;
	}

	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public void delete(Long id) {
		vehicleRepository.deleteById(id);
	}

	public Vehicle findByRegNumber(String regNumber) {
		return vehicleRepository.findByRegistrationNumber(regNumber);
	}
	
	public List<Vehicle> search(VehicleSearchDTO dto){
		
		ArrayList<Vehicle> result1 = new ArrayList<>();
		ArrayList<Vehicle> result2 = new ArrayList<>();
		
		result1 = vehicleRepository.findByTypeContainingIgnoreCaseAndGearContainingIgnoreCase(dto.getType(), dto.getGear());
		double min, max;
		
		if(dto.getMinPrice() == null) {
			min = 0;
		}else {
			min = dto.getMinPrice();
		}
		
		if(dto.getMinPrice() == null) {
			max = 999999999;
		}else {
			max = dto.getMaxPrice();
		}
		
		result2 = vehicleRepository.findByPriceRange(dto.getRentacarId(), min, max);
		
		result1.retainAll(result2);
		
		if(dto.getPickUp()!= null && dto.getDropOff() != null) {
			result2 = vehicleRepository.findByAvailability(dto.getRentacarId(), dto.getPickUp(), dto.getDropOff());
		}
		
		result1.retainAll(result2);
		
		for (Vehicle vehicle : result1) {
			vehicle.setRate(vehRateRepository.findAverageByVehicle(vehicle.getId()));
			System.out.println("Rating search: " + vehicle.getRate());
		}
		
		return result1;
	}
}

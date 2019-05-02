package rs.travel.bookingWithEase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.Vehicle;

public interface IVehicleRepository extends JpaRepository<Vehicle, Long>{
	
	Vehicle findByRegistrationNumber(String registrationNumber);

}

package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.VehicleReservation;
import rs.travel.bookingWithEase.repository.IVehicleReservationRepository;

@Service
public class VehicleReservationService {

	@Autowired
	private IVehicleReservationRepository vehicleReservationRepository;
	
	public Optional<VehicleReservation> findOne(Long id){
		return vehicleReservationRepository.findById(id);
	}
	
	public List<VehicleReservation> findAll(){
		return vehicleReservationRepository.findAll();
	}
	
	public VehicleReservation save(VehicleReservation vehRes) {
		return vehicleReservationRepository.save(vehRes);
	}
	
	public void delete(Long id) {
		vehicleReservationRepository.deleteById(id);
	}
}

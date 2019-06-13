package rs.travel.bookingWithEase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.CompanyRateDTO;
import rs.travel.bookingWithEase.model.VehicleRate;
import rs.travel.bookingWithEase.repository.IVehicleRateRepository;
import rs.travel.bookingWithEase.repository.IVehicleReservationRepository;

@Service
public class VehicleRateService {

	@Autowired
	private IVehicleRateRepository vehRateRepository;
	
	@Autowired
	private IVehicleReservationRepository vehResRepository;
	
	public double getAverageByVehicle(Long companyId) {
		Double rating = vehRateRepository.findAverageByCompany(companyId);
		
		if(rating == null) {
			return -1;
		}
		
		return rating.doubleValue();
	}
	
	public VehicleRate saveRate(CompanyRateDTO dto) {
		
		VehicleRate veh = new VehicleRate();
		veh.setRate(dto.getRate());
		veh.setReservation(vehResRepository.getOne(dto.getReservationId()));
		
		veh = vehRateRepository.save(veh);
		
		return veh;
		
	}
}

package rs.travel.bookingWithEase.service;

import org.springframework.beans.factory.annotation.Autowired;

import rs.travel.bookingWithEase.dto.CompanyRateDTO;
import rs.travel.bookingWithEase.model.FlightRate;
import rs.travel.bookingWithEase.repository.IFlightRateRepository;
import rs.travel.bookingWithEase.repository.IFlightReservationRepository;

public class FlightRateService {

	@Autowired
	private IFlightRateRepository flightRateRepository;
	
	@Autowired
	private IFlightReservationRepository flightResRepository;
	
	public FlightRate saveRate(CompanyRateDTO dto) {
		
		FlightRate fl = new FlightRate();
		fl.setRate(dto.getRate());
		fl.setReservation(flightResRepository.getOne(dto.getReservationId()));
		
		fl = flightRateRepository.save(fl);
		
		return fl;
		
	}
}

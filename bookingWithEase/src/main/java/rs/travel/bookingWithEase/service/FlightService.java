package rs.travel.bookingWithEase.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.repository.IFlightRepository;


@Service
public class FlightService  {

	@Autowired
	private IFlightRepository flightRepository;

	
	public Optional<Flight> findOne(Long id) {
		return flightRepository.findById(id);
	}

	public List<Flight> findAll() {
		return flightRepository.findAll();
	}
	
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	public void delete(Long id) {
		flightRepository.deleteById(id);
	}

	
	

}

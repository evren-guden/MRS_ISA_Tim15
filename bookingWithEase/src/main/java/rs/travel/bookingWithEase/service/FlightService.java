package rs.travel.bookingWithEase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.repository.FlightRepository;
import rs.travel.bookingWithEase.repository.MemoryStorage;

@Service
public class FlightService implements IFlightService {
	
	@Autowired
	protected MemoryStorage dbManager;

	@Autowired
	private FlightRepository repository;

	@Override
	public Flight create(Flight flight) throws Exception {
		if (flight.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		Flight savedFlight = repository.create(flight);
		return savedFlight;
	}

	@Override
	public boolean addFlight(Flight newFlight) {
		return dbManager.addFlight(newFlight);
	}

	@Override
	public boolean delFlight(Flight flightForDelete) {
		return dbManager.delFlight(flightForDelete);
	}

	@Override
	public boolean editFlight(Flight updateFlight) {
		
		return dbManager.editFlight(updateFlight);
	}

	@Override
	public Collection<Flight> deliveryFlight() {
		
		return dbManager.deliveryFlight();
	}

	@Override
	public Flight searchFlight(String idFlight) {
		
		return dbManager.searchFlight(idFlight);
	}

	
	

}

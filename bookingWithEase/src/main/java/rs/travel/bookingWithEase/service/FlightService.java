package rs.travel.bookingWithEase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.repository.FlightRepository;

@Service
public class FlightService implements IFlightService {

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

}

package rs.travel.bookingWithEase.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.Flight;

@Repository
public class FlightRepository implements IFlightRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, Flight> flights = new ConcurrentHashMap<Long, Flight>();
	
	@Override
	public Flight create(Flight flight) {

		Long id = flight.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			flight.setId(id);
		}
		this.flights.put(id, flight);
		return flight;
	}

}

package rs.travel.bookingWithEase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Company;

@Repository
public class AirlineRepository implements IAirlineRepository {
	
	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, Airline> airlines = new ConcurrentHashMap<Long, Airline>();

	@Override
	public Collection<Airline> findAll() {
		return this.airlines.values();
	}

	@Override
	public Airline create(Company company) {
		Airline airline = new Airline(company);
		Long id = airline.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			airline.setId(id);
		}
		this.airlines.put(id, airline);

		return airline;
	}

}

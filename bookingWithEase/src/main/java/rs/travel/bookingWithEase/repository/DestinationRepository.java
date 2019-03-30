package rs.travel.bookingWithEase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Destination;
import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.model.Location;

@Repository
public class DestinationRepository implements IDestinationRepository {

	
	
	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, Destination> destinations = new ConcurrentHashMap<Long, Destination>();
	
	 public DestinationRepository() {
		
			destinations.put(100l, new Destination(100l,"Airport 1","a",new Airline(),new Flight()));			
			
		}
	@Override
	public Collection<Destination> findAll() {
		// TODO Auto-generated method stub
		 return this.destinations.values();
	}

	@Override
	public Destination create(Destination d) {
		Destination destination = new Destination(d);
		Long id = destination.getIdAerodromes();

		if (id == null) {
			id = counter.incrementAndGet();
			destination.setIdAerodromes(id);
		}
		this.destinations.put(id, destination);

		return destination;
	}

	@Override
	public Destination find(Long id) {
		System.out.println("Id je: " + id);
		return this.destinations.get(id);
	}

	@Override
	public Destination update(Destination destination) {
		Long id = destination.getIdAerodromes();

		if (id != null) {
			this.destinations.put(id, destination);
		}

		return destination;
	}

}

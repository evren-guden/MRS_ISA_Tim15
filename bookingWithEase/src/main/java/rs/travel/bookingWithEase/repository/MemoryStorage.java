package rs.travel.bookingWithEase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;

import rs.travel.bookingWithEase.model.Flight;

public class MemoryStorage implements DatabaseManager {
	protected ConcurrentHashMap<Long, Flight> flight;

	@Override
	public boolean addFlight(Flight newFlight) {
		if (this.flight.containsKey(newFlight.getId())) {
			return false;
		}
		this.flight.put(newFlight.getId(), newFlight);
		return true;
	}

	@Override
	public boolean delFlight(Flight flightForDelete) {
		if (!this.flight.containsKey(flightForDelete.getId())) {
			return false;
		}
		this.flight.remove(flightForDelete.getId());
		return true;
	}

	@Override
	public boolean editFlight(Flight updateFlight) {
		if (!this.flight.containsKey(updateFlight.getId())) {
			return false;
		}
		this.flight.put(updateFlight.getId(), updateFlight);
		return true;
	}

	@Override
	public Collection<Flight> deliveryFlight() {
		return this.flight.values();
	}

	@Override
	public Flight searchFlight(String idFlight) {
		return this.flight.get(idFlight);
	}

}

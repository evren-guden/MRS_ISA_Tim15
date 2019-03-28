package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Flight;



public interface DatabaseManager {
	
	
	public boolean addFlight(Flight newFlight);
	public boolean delFlight(Flight flightForDelete);
	public boolean editFlight(Flight updateFlight);
	public Collection<Flight> deliveryFlight();
	public Flight searchFlight(String idFlight);

}

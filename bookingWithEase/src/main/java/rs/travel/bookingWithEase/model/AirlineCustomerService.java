package rs.travel.bookingWithEase.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AirlineCustomerService {
	private Long id;
	private String name;
	public AirlineCustomerService() {
		super();
	}

	private Location address;
	private String promotionalDescription;
	private Set<Destination> Destinations;
	private HashMap<String, Flight> flights;

	public AirlineCustomerService(Long id, String name, Location address, String promotionalDescription,
			Set<Destination> destinations, HashMap<String, Flight> flights, Set<Map> fastReservation,
			Set<Airplane> planes, HashMap<String, Map> soldCart, double average) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		Destinations = destinations;
		this.flights = flights;
		this.fastReservation = fastReservation;
		this.planes = planes;
		this.soldCart = soldCart;
		this.average = average;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public String getPromotionalDescription() {
		return promotionalDescription;
	}

	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}

	public Set<Destination> getDestinations() {
		return Destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		Destinations = destinations;
	}

	public HashMap<String, Flight> getFlights() {
		return flights;
	}

	public void setFlights(HashMap<String, Flight> flights) {
		this.flights = flights;
	}

	public Set<Map> getFastReservation() {
		return fastReservation;
	}

	public void setFastReservation(Set<Map> fastReservation) {
		this.fastReservation = fastReservation;
	}

	public Set<Airplane> getPlanes() {
		return planes;
	}

	public void setPlanes(Set<Airplane> planes) {
		this.planes = planes;
	}

	public HashMap<String, Map> getSoldCart() {
		return soldCart;
	}

	public void setSoldCart(HashMap<String, Map> soldCart) {
		this.soldCart = soldCart;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	private Set<Map> fastReservation;
	private Set<Airplane> planes;
	private HashMap<String, Map> soldCart;
	private double average;

}

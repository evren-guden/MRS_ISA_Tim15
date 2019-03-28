package rs.travel.bookingWithEase.model;

import java.util.Date;
import java.util.HashMap;

public class Flight {
	private Long id;
	private Destination startDestination;
	private Destination endDestination;
	private Date dateFligh;
	private Date DateLand;
	private Date timeTravel;
	private double lengthTravel;
	private HashMap<String, Destination> transitions;
	private double priceTicket;

	private String informationLuggage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Destination getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(Destination startDestination) {
		this.startDestination = startDestination;
	}

	public Destination getEndDestination() {
		return endDestination;
	}

	public void setEndDestination(Destination endDestination) {
		this.endDestination = endDestination;
	}

	public Date getDateFligh() {
		return dateFligh;
	}

	public void setDateFligh(Date dateFligh) {
		this.dateFligh = dateFligh;
	}

	public Date getDateLand() {
		return DateLand;
	}

	public void setDateLand(Date dateLand) {
		DateLand = dateLand;
	}

	public Date getTimeTravel() {
		return timeTravel;
	}

	public void setTimeTravel(Date timeTravel) {
		this.timeTravel = timeTravel;
	}

	public double getLengthTravel() {
		return lengthTravel;
	}

	public void setLengthTravel(double lengthTravel) {
		this.lengthTravel = lengthTravel;
	}

	public HashMap<String, Destination> getTransitions() {
		return transitions;
	}

	public void setTransitions(HashMap<String, Destination> transitions) {
		this.transitions = transitions;
	}

	public double getPriceTicket() {
		return priceTicket;
	}

	public void setPriceTicket(double priceTicket) {
		this.priceTicket = priceTicket;
	}

	public String getInformationLuggage() {
		return informationLuggage;
	}

	public void setInformationLuggage(String informationLuggage) {
		this.informationLuggage = informationLuggage;
	}

	public Flight(Long id, Destination startDestination, Destination endDestination, Date dateFligh, Date dateLand,
			Date timeTravel, double lengthTravel, HashMap<String, Destination> transitions, double priceTicket,
			String informationLuggage) {
		super();
		this.id = id;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.dateFligh = dateFligh;
		DateLand = dateLand;
		this.timeTravel = timeTravel;
		this.lengthTravel = lengthTravel;
		this.transitions = transitions;
		this.priceTicket = priceTicket;
		this.informationLuggage = informationLuggage;
	}

	public Flight() {
		super();
	}
}
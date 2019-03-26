package rs.travel.bookingWithEase.model;

import java.util.Date;
import java.util.HashMap;

public class Flight {
	private Long id;
	private Destination startDestination;
	private Destination endDestination;
	private Date dateTimeFligh;
	private Date DateTimeLand;
	private Date timeTravel;
	private double lengthTravel;
	private HashMap<String, Destination> transitions;
	private double priceEconomic;
	private double priceBusiness;

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

	public Date getDateTimeFligh() {
		return dateTimeFligh;
	}

	public void setDateTimeFligh(Date dateTimeFligh) {
		this.dateTimeFligh = dateTimeFligh;
	}

	public Date getDateTimeLand() {
		return DateTimeLand;
	}

	public void setDateTimeLand(Date dateTimeLand) {
		DateTimeLand = dateTimeLand;
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

	public double getPriceEconomic() {
		return priceEconomic;
	}

	public void setPriceEconomic(double priceEconomic) {
		this.priceEconomic = priceEconomic;
	}

	public double getPriceBusiness() {
		return priceBusiness;
	}

	public void setPriceBusiness(double priceBusiness) {
		this.priceBusiness = priceBusiness;
	}

	public String getInformationLuggage() {
		return informationLuggage;
	}

	public void setInformationLuggage(String informationLuggage) {
		this.informationLuggage = informationLuggage;
	}

	public Flight(Long id, Destination startDestination, Destination endDestination, Date dateTimeFligh,
			Date dateTimeLand, Date timeTravel, double lengthTravel, HashMap<String, Destination> transitions,
			double priceEconomic, double priceBusiness, String informationLuggage) {
		super();
		this.id = id;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.dateTimeFligh = dateTimeFligh;
		DateTimeLand = dateTimeLand;
		this.timeTravel = timeTravel;
		this.lengthTravel = lengthTravel;
		this.transitions = transitions;
		this.priceEconomic = priceEconomic;
		this.priceBusiness = priceBusiness;
		this.informationLuggage = informationLuggage;
	}

}

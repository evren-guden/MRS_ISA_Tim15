package rs.travel.bookingWithEase.dto;

public class FlightDTO {
	String flightNumberRegister;
	String startDestinationRegister;
	String finalDestinationRegister;
	double costOfFlight;
	String dateOfFlight;
	String dateOfArrival;
	int length;
	
	public String getFlightNumberRegister() {
		return flightNumberRegister;
	}
	public void setFlightNumberRegister(String flightNumberRegister) {
		this.flightNumberRegister = flightNumberRegister;
	}
	public String getStartDestinationRegister() {
		return startDestinationRegister;
	}
	public void setStartDestinationRegister(String startDestinationRegister) {
		this.startDestinationRegister = startDestinationRegister;
	}
	public String getFinalDestinationRegister() {
		return finalDestinationRegister;
	}
	public void setFinalDestinationRegister(String finalDestinationRegister) {
		this.finalDestinationRegister = finalDestinationRegister;
	}
	public double getCostOfFlight() {
		return costOfFlight;
	}
	public void setCostOfFlight(double costOfFlight) {
		this.costOfFlight = costOfFlight;
	}
	public String getDateOfFlight() {
		return dateOfFlight;
	}
	public void setDateOfFlight(String dateOfFlight) {
		this.dateOfFlight = dateOfFlight;
	}
	public String getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(String dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public FlightDTO() {
		super();
	}
	public FlightDTO(String flightNumberRegister, String startDestinationRegister, String finalDestinationRegister,
			double costOfFlight, String dateOfFlight, String dateOfArrival, int length) {
		super();
		this.flightNumberRegister = flightNumberRegister;
		this.startDestinationRegister = startDestinationRegister;
		this.finalDestinationRegister = finalDestinationRegister;
		this.costOfFlight = costOfFlight;
		this.dateOfFlight = dateOfFlight;
		this.dateOfArrival = dateOfArrival;
		this.length = length;
	}

	

	

}
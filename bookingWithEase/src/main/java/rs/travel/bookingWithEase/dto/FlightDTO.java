package rs.travel.bookingWithEase.dto;

public class FlightDTO {
	String flightNumberRegister;
	String startDestinationRegister;
	String finalDestinationRegister;
	double costOfFlight;
	String dateOfFlight;

	public FlightDTO() {
		super();
	}

	public FlightDTO(String flightNumberRegister, String startDestinationRegister, String finalDestinationRegister,
			double costOfFlight, String dateOfFlight) {
		super();
		this.flightNumberRegister = flightNumberRegister;
		this.startDestinationRegister = startDestinationRegister;
		this.finalDestinationRegister = finalDestinationRegister;
		this.costOfFlight = costOfFlight;
		this.dateOfFlight = dateOfFlight;
	}

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

}
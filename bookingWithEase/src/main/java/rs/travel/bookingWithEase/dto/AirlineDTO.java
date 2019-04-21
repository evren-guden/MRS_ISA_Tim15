package rs.travel.bookingWithEase.dto;

import rs.travel.bookingWithEase.model.Airline;

public class AirlineDTO {
	private String airlineNameRegister;
	private String airlineAddressRegister;
	private String airlinePromotionalDescription;

	public String getAirlineNameRegister() {
		return airlineNameRegister;
	}

	public void setAirlineNameRegister(String airlineNameRegister) {
		this.airlineNameRegister = airlineNameRegister;
	}

	public String getAirlineAddressRegister() {
		return airlineAddressRegister;
	}

	public void setAirlineAddressRegister(String airlineAddressRegister) {
		this.airlineAddressRegister = airlineAddressRegister;
	}

	public String getAirlinePromotionalDescription() {
		return airlinePromotionalDescription;
	}

	public void setAirlinePromotionalDescription(String airlinePromotionalDescription) {
		this.airlinePromotionalDescription = airlinePromotionalDescription;
	}

	public AirlineDTO() {
		super();
	}

	public AirlineDTO(String airlineNameRegister, String airlineAddressRegister, String airlinePromotionalDescription) {
		super();
		this.airlineNameRegister = airlineNameRegister;
		this.airlineAddressRegister = airlineAddressRegister;
		this.airlinePromotionalDescription = airlinePromotionalDescription;
	}
	public AirlineDTO(Airline air) {
		super();
		this.airlineNameRegister=air.getName();
		this.airlinePromotionalDescription=air.getDescription();
	}
}
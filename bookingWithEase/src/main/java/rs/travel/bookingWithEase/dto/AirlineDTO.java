package rs.travel.bookingWithEase.dto;

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

}
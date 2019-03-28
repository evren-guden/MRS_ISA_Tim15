package rs.travel.bookingWithEase.model;

public class Destination {

	String nameDestination;

	public String getNameDestination() {
		return nameDestination;
	}

	public void setNameDestination(String nameDestination) {
		this.nameDestination = nameDestination;
	}

	public Destination(String nameDestination) {
		super();
		this.nameDestination = nameDestination;
	}

	public Destination() {
		super();
	}
}
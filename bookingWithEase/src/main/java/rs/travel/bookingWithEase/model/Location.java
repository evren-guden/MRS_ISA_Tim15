package rs.travel.bookingWithEase.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Location {
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "latitude")
	private double longitude;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "city")
	private String city;
	
	
	@Column(name = "State")
	private String State;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public Location(double latitude, double longitude, String street, String number, String city, String state) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.street = street;
		this.number = number;
		this.city = city;
		State = state;
	}

}

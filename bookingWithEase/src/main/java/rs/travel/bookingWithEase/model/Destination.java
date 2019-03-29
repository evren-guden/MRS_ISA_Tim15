package rs.travel.bookingWithEase.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class Destination {
	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Destination() {
		super();
	}

	public Destination(String name) {
		super();
		this.name = name;
	}

	
	
}
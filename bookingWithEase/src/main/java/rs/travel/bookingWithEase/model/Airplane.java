package rs.travel.bookingWithEase.model;

import java.util.Set;

public class Airplane {
	private String id;
	private Set<Seat> seat;

	public Airplane(String id, Set<Seat> seat) {
		super();
		this.id = id;
		this.seat = seat;
	}

	public Airplane() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Seat> getSeat() {
		return seat;
	}

	public void setSeat(Set<Seat> seat) {
		this.seat = seat;
	}

}

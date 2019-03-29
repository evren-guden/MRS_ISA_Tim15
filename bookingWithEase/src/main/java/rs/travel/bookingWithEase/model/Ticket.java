package rs.travel.bookingWithEase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private Long idTicket;
	
	@Column(name = "traveler")
	private User traveler;
	
	@Column(name = "seat")
	private Seat seat;
	
	@Column(name = "flight")
	private Flight flight;

	public Ticket(Long idTicket, User traveler, Seat seat, Flight flight, double price) {
		super();
		this.idTicket = idTicket;
		this.traveler = traveler;
		this.seat = seat;
		this.flight = flight;
		this.price = price;
	}

	public Ticket() {
		super();
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public User getTraveler() {
		return traveler;
	}

	public void setTraveler(User traveler) {
		this.traveler = traveler;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private double price;

}

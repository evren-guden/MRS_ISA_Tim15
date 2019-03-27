package rs.travel.bookingWithEase.model;

public class Ticket {
	private Long idTicket;
	private User traveler;
	private Seat seat;
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

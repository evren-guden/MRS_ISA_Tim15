package rs.travel.bookingWithEase.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;
@Component
@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private Long idTicket;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Seat seat;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Flight flight;

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
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

	public Ticket(Long idTicket, Seat seat, Flight flight) {
		super();
		this.idTicket = idTicket;
		this.seat = seat;
		this.flight = flight;
	}

	public Ticket() {
		super();
	}

}

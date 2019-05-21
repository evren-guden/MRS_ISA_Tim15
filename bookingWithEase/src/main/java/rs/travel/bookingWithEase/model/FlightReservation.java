package rs.travel.bookingWithEase.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FlightReservation  {

	/*
	 * Be careful with reservation cancel. CascadeType for passengers, owner and
	 * flight is set to ALL. If reservation is deleted after cancel then change cascade type.
	 */

	/*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Passenger> passengers = new HashSet<>();*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FlightInvite> invites = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Flight flight;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	protected RegisteredUser f_user;

	@Column(name = "checkInDate")
	protected  Date checkInDate;

	@Column(name = "checkOutDate")
	protected  Date checkOutDate;
	
	@Column(name = "totalPrice")
	protected double totalPrice;

	public FlightReservation(Long id, Set<FlightInvite> invites, Flight flight, RegisteredUser f_user, Date checkInDate,
			Date checkOutDate, double totalPrice) {
		super();
		this.id = id;
		this.invites = invites;
		this.flight = flight;
		this.f_user = f_user;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "FlightReservation [id=" + id + ", invites=" + invites + ", flight=" + flight + ", f_user=" + f_user
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", totalPrice=" + totalPrice
				+ "]";
	}

	public FlightReservation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<FlightInvite> getInvites() {
		return invites;
	}

	public void setInvites(Set<FlightInvite> invites) {
		this.invites = invites;
	}
	@JsonIgnore
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	@JsonIgnore
	public RegisteredUser getF_user() {
		return f_user;
	}

	public void setF_user(RegisteredUser f_user) {
		this.f_user = f_user;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
package rs.travel.bookingWithEase.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dateFligh")
	private Date dateFligh;

	@Column(name = "dateLand")
	private Date dateLand;

	@Column(name = "timeTravel")
	private Date timeTravel;

	@Column(name = "lengthTravel")
	private double lengthTravel;
	
	
	

	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ticket> tickets = new HashSet<Ticket>();
	
	
	
	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Destination> transitions = new HashSet<Destination>();

	@Column(name = "priceTicket")
	private double priceTicket;
	
	
	@Column(name = "informationLuggage")
	private String informationLuggage;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Airline airline;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "start_id")
	private Destination startDestination;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "final_id")
	private Destination endDestination;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFligh() {
		return dateFligh;
	}

	public void setDateFligh(Date dateFligh) {
		this.dateFligh = dateFligh;
	}

	public Date getDateLand() {
		return dateLand;
	}

	public void setDateLand(Date dateLand) {
		this.dateLand = dateLand;
	}

	public Date getTimeTravel() {
		return timeTravel;
	}

	public void setTimeTravel(Date timeTravel) {
		this.timeTravel = timeTravel;
	}

	public double getLengthTravel() {
		return lengthTravel;
	}

	public void setLengthTravel(double lengthTravel) {
		this.lengthTravel = lengthTravel;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Set<Destination> getTransitions() {
		return transitions;
	}

	public void setTransitions(Set<Destination> transitions) {
		this.transitions = transitions;
	}

	public double getPriceTicket() {
		return priceTicket;
	}

	public void setPriceTicket(double priceTicket) {
		this.priceTicket = priceTicket;
	}

	public String getInformationLuggage() {
		return informationLuggage;
	}

	public void setInformationLuggage(String informationLuggage) {
		this.informationLuggage = informationLuggage;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Destination getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(Destination startDestination) {
		this.startDestination = startDestination;
	}

	public Destination getEndDestination() {
		return endDestination;
	}

	public void setEndDestination(Destination endDestination) {
		this.endDestination = endDestination;
	}

	public Flight(Long id, Date dateFligh, Date dateLand, Date timeTravel, double lengthTravel, Set<Ticket> tickets,
			Set<Destination> transitions, double priceTicket, String informationLuggage, Airline airline,
			Destination startDestination, Destination endDestination) {
		super();
		this.id = id;
		this.dateFligh = dateFligh;
		this.dateLand = dateLand;
		this.timeTravel = timeTravel;
		this.lengthTravel = lengthTravel;
		this.tickets = tickets;
		this.transitions = transitions;
		this.priceTicket = priceTicket;
		this.informationLuggage = informationLuggage;
		this.airline = airline;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
	}

	public Flight() {
		super();
	}

	
	
	
	
}

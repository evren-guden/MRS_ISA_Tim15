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

import rs.travel.bookingWithEase.dto.FlightDTO;

@Component
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "number")
	private String number;
	

	@Column(name = "dateFligh")
	private Date dateFligh;

	@Column(name = "dateLand")
	private Date dateLand;

	@Column(name = "timeTravel")
	private Date timeTravel;

	@Column(name = "lengthTravel")
	private int lengthTravel;
	
	
	@Column(name = "startD")
	private String startD;
	
	@Column(name = "finalD")
	private String finalD;
	
	
	

	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ticket> tickets = new HashSet<Ticket>();
	
	
	
	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Destination> transitions = new HashSet<Destination>();

	@Column(name = "priceTicket")
	private double priceTicket;
	
	
	@Column(name = "informationLuggage")
	private String informationLuggage;
    
	
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY,mappedBy="flight")
	private Set<Destination> destinations=new HashSet<Destination>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	Airline airline;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public int getLengthTravel() {
		return lengthTravel;
	}

	public void setLengthTravel(int lengthTravel) {
		this.lengthTravel = lengthTravel;
	}

	public String getStartD() {
		return startD;
	}

	public void setStartD(String startD) {
		this.startD = startD;
	}

	public String getFinalD() {
		return finalD;
	}

	public void setFinalD(String finalD) {
		this.finalD = finalD;
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

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
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

	public Flight(Long id, String number, Date dateFligh, Date dateLand, Date timeTravel, int lengthTravel,
			String startD, String finalD, Set<Ticket> tickets, Set<Destination> transitions, double priceTicket,
			String informationLuggage, Set<Destination> destinations, Airline airline, Destination startDestination,
			Destination endDestination) {
		super();
		this.id = id;
		this.number = number;
		this.dateFligh = dateFligh;
		this.dateLand = dateLand;
		this.timeTravel = timeTravel;
		this.lengthTravel = lengthTravel;
		this.startD = startD;
		this.finalD = finalD;
		this.tickets = tickets;
		this.transitions = transitions;
		this.priceTicket = priceTicket;
		this.informationLuggage = informationLuggage;
		this.destinations = destinations;
		this.airline = airline;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
	}

	public Flight() {
		super();
	}

	





	
	
	
	
}

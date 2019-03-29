package rs.travel.bookingWithEase.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Flight {

	@Id
	@GeneratedValue
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
	private HashMap<String, Destination> transitions;

	@Column(name = "priceTicket")
	private double priceTicket;
	
	
	@Column(name = "informationLuggage")
	private String informationLuggage;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Airline airline;;

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

	public HashMap<String, Destination> getTransitions() {
		return transitions;
	}

	public void setTransitions(HashMap<String, Destination> transitions) {
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

	public Flight(Long id, Destination startDestination, Destination endDestination, Date dateFligh, Date dateLand,
			Date timeTravel, double lengthTravel, HashMap<String, Destination> transitions, double priceTicket,
			String informationLuggage) {
		super();
		this.id = id;
		this.startDestination = startDestination;
		this.endDestination = endDestination;
		this.dateFligh = dateFligh;
		this.dateLand = dateLand;
		this.timeTravel = timeTravel;
		this.lengthTravel = lengthTravel;
		this.transitions = transitions;
		this.priceTicket = priceTicket;
		this.informationLuggage = informationLuggage;
	}

	public Flight() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airline == null) ? 0 : airline.hashCode());
		result = prime * result + ((dateFligh == null) ? 0 : dateFligh.hashCode());
		result = prime * result + ((dateLand == null) ? 0 : dateLand.hashCode());
		result = prime * result + ((endDestination == null) ? 0 : endDestination.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((informationLuggage == null) ? 0 : informationLuggage.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lengthTravel);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(priceTicket);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startDestination == null) ? 0 : startDestination.hashCode());
		result = prime * result + ((timeTravel == null) ? 0 : timeTravel.hashCode());
		result = prime * result + ((transitions == null) ? 0 : transitions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (airline == null) {
			if (other.airline != null)
				return false;
		} else if (!airline.equals(other.airline))
			return false;
		if (dateFligh == null) {
			if (other.dateFligh != null)
				return false;
		} else if (!dateFligh.equals(other.dateFligh))
			return false;
		if (dateLand == null) {
			if (other.dateLand != null)
				return false;
		} else if (!dateLand.equals(other.dateLand))
			return false;
		if (endDestination == null) {
			if (other.endDestination != null)
				return false;
		} else if (!endDestination.equals(other.endDestination))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (informationLuggage == null) {
			if (other.informationLuggage != null)
				return false;
		} else if (!informationLuggage.equals(other.informationLuggage))
			return false;
		if (Double.doubleToLongBits(lengthTravel) != Double.doubleToLongBits(other.lengthTravel))
			return false;
		if (Double.doubleToLongBits(priceTicket) != Double.doubleToLongBits(other.priceTicket))
			return false;
		if (startDestination == null) {
			if (other.startDestination != null)
				return false;
		} else if (!startDestination.equals(other.startDestination))
			return false;
		if (timeTravel == null) {
			if (other.timeTravel != null)
				return false;
		} else if (!timeTravel.equals(other.timeTravel))
			return false;
		if (transitions == null) {
			if (other.transitions != null)
				return false;
		} else if (!transitions.equals(other.transitions))
			return false;
		return true;
	}
	
	
	
}

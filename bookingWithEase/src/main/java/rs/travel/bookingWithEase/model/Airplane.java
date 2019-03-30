package rs.travel.bookingWithEase.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "airplane")
public class Airplane {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	Airline airline;
	
	@OneToMany(mappedBy = "airplane", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Seat> seat = new HashSet<Seat>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Set<Seat> getSeat() {
		return seat;
	}

	public void setSeat(Set<Seat> seat) {
		this.seat = seat;
	}

	public Airplane(Long id, String name, Airline airline, Set<Seat> seat) {
		super();
		this.id = id;
		this.name = name;
		this.airline = airline;
		this.seat = seat;
	}

	public Airplane() {
		super();
	}

}

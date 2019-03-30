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



@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Airplane airplane;
	
	
	@OneToMany(mappedBy = "seat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ticket> ticket = new HashSet<Ticket>();
	
	
	
	@Column(name = "row")
	private int row;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "clss")
	private CLASSS clss; // klasa
	
	@Column(name = "occupied")
	private Boolean occupied;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public CLASSS getClss() {
		return clss;
	}

	public void setClss(CLASSS clss) {
		this.clss = clss;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public Seat(int row, int number, CLASSS clss, Boolean occupied) {
		super();
		this.row = row;
		this.number = number;
		this.clss = clss;
		this.occupied = occupied;
	}

	public Seat() {
		super();
	}

}

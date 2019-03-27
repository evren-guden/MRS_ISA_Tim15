package rs.travel.bookingWithEase.model;

public class Seat {
	private int row;
	private int number;
	private CLASSS clss; // klasa
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

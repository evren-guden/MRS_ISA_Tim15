package rs.travel.bookingWithEase.dto;

import java.util.ArrayList;

import rs.travel.bookingWithEase.model.Price;

public class RoomDTO {

	private Long id;
	private int roomNumber;
	private int floorNumber;
	private int capacity;
	private double pricePerNight;
	private ArrayList<Price> prices;
	private Long hotelId;

	public RoomDTO() {
		super();
	}

	public RoomDTO(Long id, int roomNumber, int floorNumber, int capacity, double pricePerNight, Long hotelId) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.floorNumber = floorNumber;
		this.capacity = capacity;
		this.pricePerNight = pricePerNight;
		this.hotelId = hotelId;
	}

	public RoomDTO(Long id, int roomNumber, int floorNumber, int capacity, double pricePerNight, Long hotelId,
			ArrayList<Price> prices) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.floorNumber = floorNumber;
		this.capacity = capacity;
		this.pricePerNight = pricePerNight;
		this.hotelId = hotelId;
		this.prices = prices;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public ArrayList<Price> getPrices() {
		return prices;
	}

	public void setPrices(ArrayList<Price> prices) {
		this.prices = prices;
	}

}

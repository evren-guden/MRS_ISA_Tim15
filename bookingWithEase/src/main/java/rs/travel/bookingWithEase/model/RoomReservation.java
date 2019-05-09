package rs.travel.bookingWithEase.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import rs.travel.bookingWithEase.serializer.RoomSerializer;

@Component
@Entity
@JsonIgnoreProperties(value = { "user" })
public class RoomReservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonSerialize(using = RoomSerializer.class)
	private Room room;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private RegisteredUser user;

	@Column(name = "checkInDate")
	private Date checkInDate;

	@Column(name = "checkOutDate")
	private Date checkOutDate;

	@Column(name = "reservationDate")
	private Date reservationDate;

	@Column(name = "specialOffers")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<HotelSpecialOffer> specialOffers = new HashSet<HotelSpecialOffer>();

	@Column(name = "totalPrice")
	private double totalPrice;

	public RoomReservation() {
		super();
	}

	public RoomReservation(Long id, Room room, RegisteredUser user, Date checkInDate, Date checkOutDate,
			double totalPrice) {
		super();
		this.id = id;
		this.room = room;
		this.user = user;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
	}

	public RoomReservation(Long id, Room room, RegisteredUser user, Date checkInDate, Date checkOutDate,
			HashSet<HotelSpecialOffer> specialOffers, double totalPrice) {
		super();
		this.id = id;
		this.room = room;
		this.user = user;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.reservationDate = new Date();
		this.specialOffers = specialOffers;
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "RoomReservation [id=" + id + ", room=" + room + ", user=" + user + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", reservationDate=" + reservationDate + ", specialOffers="
				+ specialOffers + ", totalPrice=" + totalPrice + "]";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Set<HotelSpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(Set<HotelSpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}

}

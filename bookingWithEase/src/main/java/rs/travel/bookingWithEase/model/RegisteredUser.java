package rs.travel.bookingWithEase.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class RegisteredUser extends User {
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RoomReservation> roomReservations = new HashSet<RoomReservation>();
	
	@OneToMany(mappedBy="vehicle_user",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private Set<VehicleReservation> vehicleReservations = new HashSet<VehicleReservation>();
	
	public RegisteredUser() {
		super();
	}

	public RegisteredUser(Long id, String username, String firstName, String lastName, String email, String password,
			String city, String telephoneNumber, String passportNumber) {
		super(id, username, firstName, lastName, email, password, city, telephoneNumber, passportNumber);

	}

}

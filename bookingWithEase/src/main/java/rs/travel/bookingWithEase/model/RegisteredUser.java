package rs.travel.bookingWithEase.model;

public class RegisteredUser extends User {

	public RegisteredUser() {
		super();
	}

	public RegisteredUser(Long id, String username, String firstName, String lastName, String email, String password,
			String city, String telephoneNumber, String passportNumber) {
		super(id, username, firstName, lastName, email, password, city, telephoneNumber, passportNumber);

	}

}

package rs.travel.bookingWithEase.model;

public class Admin extends User {
	
	private ADMIN_TYPE type;

	public Admin() {
		super();
		
	}

	public Admin(Long id, String username, String firstName, String lastName, String email, String password,
			String city, String telephoneNumber, String passportNumber) {
		super(id, username, firstName, lastName, email, password, city, telephoneNumber, passportNumber);
		
	}
	
	public Admin(Long id, String username, String firstName, String lastName, String email, String password,
			String city, String telephoneNumber, String passportNumber,ADMIN_TYPE type) {
		super(id, username, firstName, lastName, email, password, city, telephoneNumber, passportNumber);
		this.type = type;
	}

	public ADMIN_TYPE getType() {
		return type;
	}

	public void setType(ADMIN_TYPE type) {
		this.type = type;
	}
	
	
}

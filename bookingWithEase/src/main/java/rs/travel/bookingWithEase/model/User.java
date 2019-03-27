package rs.travel.bookingWithEase.model;

public class User {
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;

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

	public String getSurname() {
		return surname;
	}

	public User() {
		super();
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumberTelephone() {
		return numberTelephone;
	}

	public void setNumberTelephone(String numberTelephone) {
		this.numberTelephone = numberTelephone;
	}

	public String getNumberPassport() {
		return numberPassport;
	}

	public void setNumberPassport(String numberPassport) {
		this.numberPassport = numberPassport;
	}

	public User(Long id, String name, String surname, String email, String password, String city,
			String numberTelephone, String numberPassport) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.numberTelephone = numberTelephone;
		this.numberPassport = numberPassport;
	}

	private String city;
	private String numberTelephone;
	private String numberPassport;

}

package rs.travel.bookingWithEase.model;

import org.springframework.stereotype.Component;

@Component
public class RentACar extends Company {

	private static final long serialVersionUID = 1L;

	public RentACar() {
		super();
	}

	public RentACar(long id, String name, String address, String description, double rating) {
		super(id, name, address, description, rating);

	}
	
	public RentACar(Company company)
	{
		this.id = company.id;
		this.name = company.name;
		this.address = company.address;
		this.description = company.description;
		this.rating = company.rating;
	}
	
	
	
}

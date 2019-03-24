package rs.travel.bookingWithEase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.repository.AirlineRepository;

@Service
public class AirlineService implements IAirlineService{
	
	@Autowired
	private AirlineRepository airlines;
	
	@Override
	public Collection<Airline> findAll()
	{
		return airlines.findAll();
	}
	
	@Override
	public Airline create(Company company) throws Exception
	{
		Airline airline = new Airline(company);
		if (airline.getId() != null) {
			throw new Exception(" ");
		}

		return airlines.create(airline);
		
	}
}

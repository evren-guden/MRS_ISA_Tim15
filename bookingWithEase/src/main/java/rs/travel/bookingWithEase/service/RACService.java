package rs.travel.bookingWithEase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.repository.RACRepository;

@Service
public class RACService implements IRACService{
	
	@Autowired
	private RACRepository rentacars;
	
	
	@Override
	public Collection<RentACar> findAll()
	{
		return rentacars.findAll();
		
	}
	
	@Override
	public RentACar create(Company company) throws Exception
	{	
		RentACar rentACar = new RentACar(company);
		if (rentACar.getId() != null) {
			throw new Exception(" ");
		}

		return rentacars.create(rentACar);
		
	}

}

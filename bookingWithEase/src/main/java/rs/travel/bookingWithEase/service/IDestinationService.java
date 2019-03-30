package rs.travel.bookingWithEase.service;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Destination;

public interface IDestinationService {
	
	Collection<Destination> findAll();

	Destination create(Destination d) throws Exception;
	
	Destination find(Long id);
	
	Destination update(Destination destination)throws Exception;

}

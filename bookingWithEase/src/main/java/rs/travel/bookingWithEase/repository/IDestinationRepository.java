package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Destination;


public interface IDestinationRepository {
	Collection<Destination> findAll();

	Destination create(Destination d);
	
	Destination find(Long id);
	
	Destination update(Destination destination);

}

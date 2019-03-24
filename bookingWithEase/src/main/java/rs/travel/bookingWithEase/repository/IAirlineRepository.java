package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Company;

public interface IAirlineRepository {
	
	Collection<Airline> findAll();

	Airline create(Company company);
	
}

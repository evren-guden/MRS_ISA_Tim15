package rs.travel.bookingWithEase.service;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.model.Company;

public interface IAirlineService {
	
	Collection<Airline> findAll();
	
	Airline create(Company company) throws Exception;
}

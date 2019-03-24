package rs.travel.bookingWithEase.service;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.RentACar;

public interface IRACService {
	
	Collection<RentACar> findAll();
	
	RentACar create(Company company) throws Exception;
	
}

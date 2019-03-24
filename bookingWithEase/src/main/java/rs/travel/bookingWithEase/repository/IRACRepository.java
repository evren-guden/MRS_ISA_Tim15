package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.RentACar;

public interface IRACRepository {
	
	Collection<RentACar> findAll();

	RentACar create(Company company);
	
}

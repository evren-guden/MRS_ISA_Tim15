package rs.travel.bookingWithEase.service;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;

public interface IHotelService {
	
	Collection<Hotel> findAll();
	Hotel find(Long id);
	Hotel create(Company company) throws Exception;
	Hotel update(Hotel hotel) throws Exception;
	void delete(Long id);

}

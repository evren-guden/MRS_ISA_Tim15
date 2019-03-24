package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import rs.travel.bookingWithEase.model.Hotel;

public interface IHotelRepository {
	
	Collection<Hotel> findAll();

	Hotel create(Hotel hotel);

	Hotel find(Long id);

	Hotel update(Hotel hotel);

	void delete(Long id);
	
}

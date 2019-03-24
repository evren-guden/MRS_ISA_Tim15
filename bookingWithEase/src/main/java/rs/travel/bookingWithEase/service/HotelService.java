package rs.travel.bookingWithEase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.repository.HotelRepository;

@Service
public class HotelService implements IHotelService {
	
	@Autowired
	private HotelRepository hotels;
	
	@Override
	public Collection<Hotel> findAll() {
		
		return hotels.findAll();
	}
	
	@Override
	public Hotel find(Long id) {
		return hotels.find(id);
		
	}
	
	@Override
	public Hotel create(Company company) throws Exception {
		
		Hotel hotel = new Hotel(company);
		if (hotel.getId() != null) {
			throw new Exception("");
		}

		return hotels.create(hotel);
		
	}
	
	@Override
	public Hotel update(Hotel hotel) throws Exception {
		
		return null;
	}

	@Override
	public void delete(Long id) {
		
	}

}

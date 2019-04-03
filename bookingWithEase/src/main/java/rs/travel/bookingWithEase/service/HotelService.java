package rs.travel.bookingWithEase.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.repository.IHotelRepository;

@Service
public class HotelService{
	
	@Autowired
	private IHotelRepository hotels;
	
	public Optional<Hotel> findOne(Long id) {
		return hotels.findById(id);
	}

	public List<Hotel> findAll() {
		return hotels.findAll();
	}
	
	public Hotel findById(Long id) {
		  
		Optional<Hotel> dbHotel = hotels.findById(id);
		if (dbHotel.isPresent()) {
			return dbHotel.get();
		} else {
			return null;
		}
	}
	
	public Hotel save(Hotel hotel) {
		return hotels.save(hotel);
	}

	public void delete(Long id) {
		hotels.deleteById(id);
	}

	public Collection<RentACar> search(RentACarDTO rentACar) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean addRoom(Room room)
	{
		
		return true;
	}
	

}

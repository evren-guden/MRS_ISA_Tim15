package rs.travel.bookingWithEase.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.RentACar;
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
	
	public Optional<Hotel> getById(Long id) {
		
		return this.hotels.findById(id);
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


}

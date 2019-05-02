package rs.travel.bookingWithEase.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.dto.HotelSearchDTO;
import rs.travel.bookingWithEase.model.Admin;
import rs.travel.bookingWithEase.repository.IHotelRepository;
import rs.travel.bookingWithEase.repository.IUserRepository;

@Service
public class HotelService {

	@Autowired
	private IHotelRepository hotels;

	@Autowired
	private IUserRepository users;

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
		for (Admin admin : hotel.getAdmins()) {
			System.out.println("hotel: " + hotel.getName() + " admin: " + admin.getUsername());
			admin.setCompany(hotel);
			users.save(admin);
		}
		return hotels.save(hotel);
	}

	public void delete(Long id) {
		hotels.deleteById(id);
	}

	public Collection<Hotel> search(HotelSearchDTO hotelSearchDTO) {

		ArrayList<Hotel> result1 = new ArrayList<Hotel>();
		ArrayList<Hotel> result2 = new ArrayList<Hotel>();

		if (hotelSearchDTO.getStars() == 0 && hotelSearchDTO.getRating() == 0)
			result1 = hotels.findByNameAndAddress(hotelSearchDTO.getName(), hotelSearchDTO.getAddress());
		else if (hotelSearchDTO.getRating() == 0)
			result1 = hotels.findByNameAddressStars(hotelSearchDTO.getName(), hotelSearchDTO.getAddress(),
					hotelSearchDTO.getStars());
		else if (hotelSearchDTO.getStars() == 0)
			result1 = hotels.findByNameAddressRating(hotelSearchDTO.getName(), hotelSearchDTO.getAddress(),
					hotelSearchDTO.getRating());
		else 
			result1 = hotels.findByNameAddressStarsRating(hotelSearchDTO.getName(), hotelSearchDTO.getAddress(),
				hotelSearchDTO.getStars(), hotelSearchDTO.getRating());

		if (hotelSearchDTO.getCheckIn() != null && hotelSearchDTO.getCheckOut() != null) {
			result2 = hotels.findByRoomAvailability(hotelSearchDTO.getCheckIn(), hotelSearchDTO.getCheckOut());
			result1.retainAll(result2); // intersection
		}
		
		return result1;

	}

	public boolean addRoom(Room room) {

		return true;
	}

}

package rs.travel.bookingWithEase.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.model.Admin;
import rs.travel.bookingWithEase.repository.IHotelRepository;
import rs.travel.bookingWithEase.repository.IUserRepository;

@Service
public class HotelService{
	
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
		for(Admin admin : hotel.getAdmins())
		{	
			System.out.println("hotel: " + hotel.getName() + " admin: " + admin.getUsername());
			admin.setCompany(hotel);
			users.save(admin);		
		}
		return hotels.save(hotel);
	}

	public void delete(Long id) {
		hotels.deleteById(id);
	}
	
	public Collection<Hotel> search(Hotel hotelFind) {
	
		ConcurrentMap<Long, Hotel> searchHotel = new ConcurrentHashMap<Long, Hotel>();
		System.out.println("id: " + hotelFind.getId() + " name " + hotelFind.getId() + " address " + hotelFind.getAddress());
		for (Hotel hotel : hotels.findAll()) {
			if (hotelFind.getId() == null || hotelFind.getId() == hotel.getId()) {
				if (hotelFind.getAddress() == null
						|| hotel.getAddress().toLowerCase().contains(hotelFind.getAddress().toLowerCase())) {
					if (hotelFind.getName() == null
							|| hotel.getName().toLowerCase().contains(hotelFind.getName().toLowerCase())) {
						searchHotel.put(hotel.getId(), hotel);
					}
				}
			}
		}
		return searchHotel.values();
	}
	
	public boolean addRoom(Room room)
	{
		
		return true;
	}
	

}

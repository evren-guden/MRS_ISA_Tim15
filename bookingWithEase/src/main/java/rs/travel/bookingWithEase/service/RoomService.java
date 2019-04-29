package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.repository.IHotelRepository;
import rs.travel.bookingWithEase.repository.IRoomRepository;

@Service
public class RoomService {

	@Autowired
	private IRoomRepository rooms;
	
	@Autowired
	private IHotelRepository hotels;
	
	@Autowired 
	private HotelService hotelService;

	public Room findById(Long id) {
		Optional<Room> dbRoom = rooms.findById(id);
		if (dbRoom.isPresent()) {
			return dbRoom.get();
		} else {
			return null;
		}
	}

	public List<Room> findAll() {
		return rooms.findAll();
	}
	
	public List<Room> findByHotelId(Long id)
	{	
		return rooms.findByHotel(hotels.findById(id).get());
	}

	public Room save(Room room) {
		return rooms.save(room);
	}

	public void delete(Long id) {
		rooms.deleteById(id);
	}
	
	public Room dtoToRoom(RoomDTO roomDto)
	{
		Hotel hotel = hotelService.findById(roomDto.getHotelId());
		if(hotel == null)
		{
			return null;
		}
		
		Room room = new Room(roomDto.getId(),roomDto.getRoomNumber(), roomDto.getFloorNumber(),
							 roomDto.getCapacity(), roomDto.getPricePerNight(), 
							 hotel);		
		return room;
	}


}

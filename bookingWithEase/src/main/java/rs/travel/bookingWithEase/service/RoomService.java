package rs.travel.bookingWithEase.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.dto.RoomSearchDTO;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.Price;
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
	
	public List<Room> search(RoomSearchDTO roomSearchDTO)
	{	
		ArrayList<Room> result1 = new ArrayList<Room>();
		ArrayList<Room> result2 = new ArrayList<Room>();
		
		if (roomSearchDTO.getCapacity() == 0 && roomSearchDTO.getFloorNumber() == -11) {
			result1 = rooms.findByPriceRange(roomSearchDTO.getHotelId(), roomSearchDTO.getMinPrice(), roomSearchDTO.getMaxPrice());

		} else if (roomSearchDTO.getCapacity() == 0) {
			result1 = rooms.findByPriceRangeAndFloorNumber(roomSearchDTO.getHotelId(), roomSearchDTO.getFloorNumber(),
					roomSearchDTO.getMinPrice(), roomSearchDTO.getMaxPrice());
		} else if (roomSearchDTO.getFloorNumber() == -11) {
			result1 = rooms.findByPriceRangeAndCapacity(roomSearchDTO.getHotelId(), roomSearchDTO.getCapacity(), roomSearchDTO.getMinPrice(),
					roomSearchDTO.getMaxPrice());
		}else {
			result1 = rooms.search(roomSearchDTO.getHotelId(),roomSearchDTO.getCapacity(), roomSearchDTO.getFloorNumber(),
					roomSearchDTO.getMinPrice(), roomSearchDTO.getMaxPrice());
		}
		
		if (roomSearchDTO.getCheckIn() != null && roomSearchDTO.getCheckOut() != null) {
			result2 = rooms.findByAvailability(roomSearchDTO.getHotelId(), roomSearchDTO.getCheckIn(), roomSearchDTO.getCheckOut());
			for(Room r : result2)
			{
				System.out.println("\n\n room id " + r.getId()+ "\n\n");
			}
			result1.retainAll(result2); // intersection
		}
		
		return result1;
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
		room.setPrices(new HashSet<Price>(roomDto.getPrices()));
		return room;
	}


}

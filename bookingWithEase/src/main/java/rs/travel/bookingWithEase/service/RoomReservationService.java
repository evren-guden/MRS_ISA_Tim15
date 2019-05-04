package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RoomReservationDTO;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.model.RoomReservation;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.repository.IHotelRepository;
import rs.travel.bookingWithEase.repository.IRoomRepository;
import rs.travel.bookingWithEase.repository.IRoomReservationRepository;
import rs.travel.bookingWithEase.repository.IUserRepository;

@Service
public class RoomReservationService {
	
	@Autowired
	private IRoomReservationRepository roomReservations;
	
	@Autowired 
	private IUserRepository users;
	
	@Autowired 
	private IRoomRepository rooms;
	
	public Optional<RoomReservation> findOne(Long id){
		return roomReservations.findById(id);
	}
	
	public List<RoomReservation> findAll(){
		return roomReservations.findAll();
	}
	
	public List<RoomReservation> findByUser(RegisteredUser u)
	{
		return roomReservations.findByUser(u);
	}
	
	public RoomReservation save(RoomReservation roomRes) {
		return roomReservations.save(roomRes);
	}
	
	public void delete(Long id) {
		roomReservations.deleteById(id);
	}
	
	public RoomReservation dtoToReservation(RoomReservationDTO dto)
	{
		// TODO Validation
		RegisteredUser u = (RegisteredUser) users.findById(dto.getUserId()).get();
		Room r = (Room) rooms.getOne(dto.getRoomId());
		// TODO recalculate total price
		return new RoomReservation(0l,r,u,dto.getCheckIn(),dto.getCheckOut(),dto.getTotalPrice());
	}
}

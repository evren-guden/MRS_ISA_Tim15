package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.RoomReservation;
import rs.travel.bookingWithEase.repository.IRoomReservationRepository;

@Service
public class RoomReservationService {
	
	@Autowired
	private IRoomReservationRepository roomReservations;
	
	public Optional<RoomReservation> findOne(Long id){
		return roomReservations.findById(id);
	}
	
	public List<RoomReservation> findAll(){
		return roomReservations.findAll();
	}
	
	public RoomReservation save(RoomReservation roomRes) {
		return roomReservations.save(roomRes);
	}
	
	public void delete(Long id) {
		roomReservations.deleteById(id);
	}
}

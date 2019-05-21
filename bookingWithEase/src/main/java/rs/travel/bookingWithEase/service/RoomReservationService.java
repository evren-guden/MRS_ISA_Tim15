package rs.travel.bookingWithEase.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RoomReservationDTO;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.model.RoomReservation;
import rs.travel.bookingWithEase.model.HotelSpecialOffer;
import rs.travel.bookingWithEase.model.QuickRoomReservation;
import rs.travel.bookingWithEase.repository.IHotelSpecialOfferRepository;
import rs.travel.bookingWithEase.repository.IRoomRepository;
import rs.travel.bookingWithEase.repository.IRoomReservationRepository;

@Service
public class RoomReservationService {

	@Autowired
	private IRoomReservationRepository roomReservations;

	@Autowired
	private UserService userService;

	@Autowired
	private IRoomRepository rooms;
	
	@Autowired
	private IHotelSpecialOfferRepository hotelSpecialOffers;

	public RoomReservation findOne(Long id) {
		Optional<RoomReservation> rrOpt = roomReservations.findById(id);

		if (rrOpt.isPresent()) {
			return rrOpt.get();
		}  

		return null;
	}

	public List<RoomReservation> findAll() {
		return roomReservations.findAll();
	}

	public List<RoomReservation> findByUser(RegisteredUser u) {
		return roomReservations.findByUser(u);
	}

	public RoomReservation save(RoomReservation roomRes) {
		return roomReservations.save(roomRes);
	}
	
	public void delete(Long id)
	{
		roomReservations.deleteById(id);
	}

	public void cancelReservation(Long id) {
		
		RoomReservation rr = roomReservations.getOne(id);
		if(rr instanceof QuickRoomReservation)
		{
			rr.setUser(null);
			save(rr);
		}else
		{	
			roomReservations.delete(rr);
		}
	}

	public RoomReservation dtoToReservation(RoomReservationDTO dto) {
		// TODO Validation
		RegisteredUser u = null;
		try {
			u = (RegisteredUser) userService.findOne(dto.getUserId());
		} catch (Exception e) {
		}
		Room r = (Room) rooms.getOne(dto.getRoomId());
		HashSet<HotelSpecialOffer> soSet = new HashSet<HotelSpecialOffer>();
		
		for(Long id : dto.getSpecialOffers())
		{
			HotelSpecialOffer so = hotelSpecialOffers.getOne(id);
			soSet.add(so);
		}
		
		// TODO recalculate total price
		return new RoomReservation(0l, r, u, dto.getCheckIn(), dto.getCheckOut(), soSet, dto.getTotalPrice());
	}
}

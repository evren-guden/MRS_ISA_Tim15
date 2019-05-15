package rs.travel.bookingWithEase.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.DefiningQrrDTO;
import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.model.HotelSpecialOffer;
import rs.travel.bookingWithEase.model.QuickRoomReservation;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.repository.IHotelSpecialOfferRepository;
import rs.travel.bookingWithEase.repository.IQuickRoomReservationRepository;
import rs.travel.bookingWithEase.repository.IRoomRepository;

@Service
public class QuickRoomReservationService {

	@Autowired
	private IQuickRoomReservationRepository quickRoomReservations;

	@Autowired
	private IHotelSpecialOfferRepository hotelSpecialOffers;
	
	@Autowired
	private IRoomRepository rooms;
	
	@Autowired 
	private RoomService roomService;
	
	public Optional<QuickRoomReservation> findOne(Long id) {
		return quickRoomReservations.findById(id);
	}

	public List<QuickRoomReservation> findAll() {
		return quickRoomReservations.findAll();
	}

	public List<QuickRoomReservation> findByUser(RegisteredUser u) {
		return quickRoomReservations.findByUser(u);
	}

	public QuickRoomReservation save(QuickRoomReservation roomRes) {
		return quickRoomReservations.save(roomRes);
	}

	public void delete(Long id) {
		quickRoomReservations.deleteById(id);
	}

	public List<QuickRoomReservation> dtoToReservations(DefiningQrrDTO dto) {
		
		HashSet<HotelSpecialOffer> soSet = new HashSet<HotelSpecialOffer>();

		for (Long id : dto.getSpecialOffers()) {
			HotelSpecialOffer so = hotelSpecialOffers.getOne(id);
			System.out.println("\n So added: " + so.getName() + " " + so.getId());
			soSet.add(so);
		}
		
		ArrayList<QuickRoomReservation> reservations = new ArrayList<QuickRoomReservation>();
		QuickRoomReservation qrr = new QuickRoomReservation(); 
		
		for(Long roomId : dto.getRooms())
		{	
			// TODO Validation
			Room r = rooms.findById(roomId).get();
			RoomDTO roomDto = roomService.roomToDTO(r);
			roomService.calculateTotalPrice(roomDto, dto.getCheckIn(), dto.getCheckOut());
			System.out.println("Total price is: " + roomDto.getTotalPrice());
			
			qrr =  new QuickRoomReservation(0l, r, null, dto.getCheckIn(), dto.getCheckOut(), soSet, roomDto.getTotalPrice(), dto.getDiscount());
			reservations.add(qrr);
			System.out.println("\nReservation: " + qrr);
		}

		return reservations;
	}
}

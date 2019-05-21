package rs.travel.bookingWithEase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.DefiningQrrDTO;
import rs.travel.bookingWithEase.dto.RoomDTO;
import rs.travel.bookingWithEase.dto.RoomSearchDTO;
import rs.travel.bookingWithEase.model.HotelSpecialOffer;
import rs.travel.bookingWithEase.model.QuickRoomReservation;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.Room;
import rs.travel.bookingWithEase.repository.IHotelSpecialOfferRepository;
import rs.travel.bookingWithEase.repository.IQuickRoomReservationRepository;

@Service
public class QuickRoomReservationService {

	@Autowired
	private IQuickRoomReservationRepository quickRoomReservations;

	@Autowired
	private IHotelSpecialOfferRepository hotelSpecialOffers;
	
	@Autowired 
	private RoomService roomService;
	
	@Autowired 
	private UserService userService;
	
	public QuickRoomReservation findOne(Long id) {
		Optional<QuickRoomReservation> qrrOpt = quickRoomReservations.findById(id);
		if (qrrOpt.isPresent()) {
			return qrrOpt.get();
		}
		
		return null;
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
	
	public QuickRoomReservation bookQrr(Long qrrId, Long userId) {
		
		RegisteredUser u = (RegisteredUser) userService.findOne(userId);
		QuickRoomReservation qrr = findOne(qrrId);
		qrr.setUser(u);
		qrr.setReservationDate(new Date());
		save(qrr);
		return qrr;
	}
	
	public List<QuickRoomReservation> search(RoomSearchDTO roomSearchDTO) {
		ArrayList<QuickRoomReservation> result1 = new ArrayList<>();
		ArrayList<QuickRoomReservation> result2 = new ArrayList<>();

		if (roomSearchDTO.getCapacity() == 0 && roomSearchDTO.getFloorNumber() == -11) {
			result1 = quickRoomReservations.findByPriceRange(roomSearchDTO.getHotelId(), roomSearchDTO.getMinPrice(),
					roomSearchDTO.getMaxPrice());

		} else if (roomSearchDTO.getCapacity() == 0) {
			result1 = quickRoomReservations.findByPriceRangeAndFloorNumber(roomSearchDTO.getHotelId(), roomSearchDTO.getFloorNumber(),
					roomSearchDTO.getMinPrice(), roomSearchDTO.getMaxPrice());
		} else if (roomSearchDTO.getFloorNumber() == -11) {
			result1 = quickRoomReservations.findByPriceRangeAndCapacity(roomSearchDTO.getHotelId(), roomSearchDTO.getCapacity(),
					roomSearchDTO.getMinPrice(), roomSearchDTO.getMaxPrice());
		} else {
			result1 = quickRoomReservations.search(roomSearchDTO.getHotelId(), roomSearchDTO.getCapacity(),
					roomSearchDTO.getFloorNumber(), roomSearchDTO.getMinPrice(), roomSearchDTO.getMaxPrice());
		}
		
		// dates
		if (roomSearchDTO.getCheckIn() != null && roomSearchDTO.getCheckOut() != null) {
			result2 = quickRoomReservations.findByCheckInAndCheckOut(roomSearchDTO.getHotelId(), roomSearchDTO.getCheckIn(),
					roomSearchDTO.getCheckOut());
			result1.retainAll(result2); // intersection
		}else if(roomSearchDTO.getCheckIn() != null && roomSearchDTO.getCheckOut() == null)
		{
			result2 = quickRoomReservations.findByCheckIn(roomSearchDTO.getHotelId(), roomSearchDTO.getCheckIn());
			result1.retainAll(result2); // intersection
		}
		
		for (QuickRoomReservation r : result2) {
			System.out.println("\n\n qrr id " + r.getId() + "\n\n");
		}
		
		result1.removeIf(qrr -> (qrr.getUser() != null));
		return result1;
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
			Room r = roomService.findById(roomId);
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

package rs.travel.bookingWithEase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.QuickRoomReservation;
import rs.travel.bookingWithEase.model.RegisteredUser;

public interface IQuickRoomReservationRepository extends JpaRepository<QuickRoomReservation, Long>  {
	
	List<QuickRoomReservation> findByUser(RegisteredUser u);
}

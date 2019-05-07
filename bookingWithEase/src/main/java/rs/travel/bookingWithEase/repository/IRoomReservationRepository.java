package rs.travel.bookingWithEase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.model.RoomReservation;

@Repository
public interface IRoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	
	List<RoomReservation> findByUser(RegisteredUser u);
}

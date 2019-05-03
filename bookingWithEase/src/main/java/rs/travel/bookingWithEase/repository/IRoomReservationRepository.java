package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.RoomReservation;

@Repository
public interface IRoomReservationRepository extends JpaRepository<RoomReservation, Long> {

}

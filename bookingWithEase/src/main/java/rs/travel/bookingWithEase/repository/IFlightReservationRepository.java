package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.FlightReservation;
import rs.travel.bookingWithEase.model.RegisteredUser;

public interface IFlightReservationRepository extends JpaRepository<FlightReservation, Long>{

	FlightReservation findByFUserAndFlightId(RegisteredUser user, Long flightId);

}

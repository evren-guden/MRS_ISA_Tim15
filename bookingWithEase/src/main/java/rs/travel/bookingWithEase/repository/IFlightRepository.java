package rs.travel.bookingWithEase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.model.FlightReservation;


public interface IFlightRepository extends JpaRepository<Flight, Long>{

	List<Flight> findByAirlineId(Long id);

	Flight findByFlightReservations(FlightReservation flightReservation);
	
}

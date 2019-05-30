package rs.travel.bookingWithEase.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.FlightReservation;
import rs.travel.bookingWithEase.model.RegisteredUser;
import rs.travel.bookingWithEase.repository.IFlightReservationRepository;

@Service
public class FlightReservationService {

	@Autowired
	private IFlightReservationRepository flightReservationRepository;

	public FlightReservation findById(Long id) {
		Optional<FlightReservation> flightResOpt = flightReservationRepository.findById(id);
		if (flightResOpt.isPresent()) {
			return flightResOpt.get();
		}
		return null;
	}

	public FlightReservation save(FlightReservation flightReservation) {
		return flightReservationRepository.save(flightReservation);
	}

	public FlightReservation findByFUserAndFlightId(RegisteredUser user, Long flightId) {
		return flightReservationRepository.findByFUserAndFlightId(user, flightId);
	}
}

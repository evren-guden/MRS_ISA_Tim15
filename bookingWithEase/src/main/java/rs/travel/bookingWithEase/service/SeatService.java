package rs.travel.bookingWithEase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Seat;
import rs.travel.bookingWithEase.repository.ISeatRepository;

@Service
public class SeatService {

	@Autowired
	ISeatRepository seatRepository;

	public List<Seat> findByFlightId(Long flightId) {
		return seatRepository.findByFlightIdOrderBySeatNumberAsc(flightId);
	}

	public Seat findById(Long seatId) {
		return seatRepository.getOne(seatId);
	}
	
}
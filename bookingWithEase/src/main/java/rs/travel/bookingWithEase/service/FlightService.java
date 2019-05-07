package rs.travel.bookingWithEase.service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.FlightDTO;
import rs.travel.bookingWithEase.model.Flight;
import rs.travel.bookingWithEase.repository.IFlightRepository;


@Service
public class FlightService  {

	@Autowired
	private IFlightRepository flightRepository;

	public Optional<Flight> findOne(Long id) {
		return flightRepository.findById(id);
	}

	public List<Flight> findAll() {
		return flightRepository.findAll();
	}
	
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	public void delete(Long id) {
		flightRepository.deleteById(id);
	}

	public List<Flight> findAllByAirlineId(Long id) {
		return flightRepository.findByAirlineId(id);
	}
	
	public Collection<Flight> search(FlightDTO flightDto) {
		ConcurrentMap<Long, Flight> searchFlights = new ConcurrentHashMap<Long, Flight>();

		for (Flight fl : flightRepository.findAll()) {
			if (flightDto.getId() == null || flightDto.getId() == fl.getId()) {
				if (flightDto.getNumber() == null
						|| fl.getNumber().toLowerCase().contains(flightDto.getNumber().toLowerCase())) {
					if (flightDto.getStartDestination() == null
							|| fl.getStartDestination().getName().toLowerCase().contains(flightDto.getStartDestination().toLowerCase())) {
						if (flightDto.getEndDestination() == null
								|| fl.getEndDestination().getName().toLowerCase().contains(flightDto.getEndDestination().toLowerCase())) {
							if (flightDto.getDateFligh() == null 
								|| flightDto.getDateFligh().compareTo(fl.getDateFligh()) == 0) {
								if (flightDto.getPriceTicket() == 0 
									|| fl.getPriceTicket() == flightDto.getPriceTicket()) {
									if (flightDto.getDateLand() == null 
											|| flightDto.getDateLand().compareTo(fl.getDateLand()) == 0) {
										searchFlights.put(fl.getId(), fl);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return searchFlights.values();
	}

}

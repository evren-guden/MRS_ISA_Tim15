package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Destination;


import rs.travel.bookingWithEase.repository.IDestinationRepository;


@Service
public class DestinationService {
	
	@Autowired
	private IDestinationRepository destRepository;

	
	public Optional<Destination> findOne(Long id) {
		return destRepository.findById(id);
	}

	public List<Destination> findAll() {
		return destRepository.findAll();
	}
	
	public Destination save(Destination destination) {
		return destRepository.save(destination);
	}

	public void delete(Long id) {
		destRepository.deleteById(id);
	}
	
	public List<Destination> findByAirlineId(long companyId) {
		return destRepository.findByAirlineId(companyId);
	}
	
}

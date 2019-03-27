package rs.travel.bookingWithEase.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.repository.IRACRepository;

@Service
public class RACService{
	
	@Autowired
	private IRACRepository rentacarsRepository;
	
	public Optional<RentACar> findOne(Long id) {
		return rentacarsRepository.findById(id);
	}

	public List<RentACar> findAll() {
		return rentacarsRepository.findAll();
	}
	
	public RentACar save(RentACar rentACar) {
		return rentacarsRepository.save(rentACar);
	}

	public void delete(Long id) {
		rentacarsRepository.deleteById(id);
	}

	public Collection<RentACar> search(RentACarDTO rentACar) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

package rs.travel.bookingWithEase.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.repository.IRACRepository;

@Service
public class RACService {

	@Autowired
	private IRACRepository rentacarsRepository;

	public RentACar findOne(Long id) {
		Optional<RentACar> rac = rentacarsRepository.findById(id);
		
		if(rac.isPresent()) {
			return rac.get();
		}
		
		return null;
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

		ConcurrentMap<Long, RentACar> searchRAC = new ConcurrentHashMap<Long, RentACar>();

		for (RentACar rac : rentacarsRepository.findAll()) {
			if (rentACar.getId() == null || rentACar.getId() == rac.getId()) {
				if (rentACar.getAddress() == null
						|| rac.getAddress().toLowerCase().contains(rentACar.getAddress().toLowerCase())) {
					if (rentACar.getName() == null
							|| rac.getName().toLowerCase().contains(rentACar.getName().toLowerCase())) {
						searchRAC.put(rac.getId(), rac);
					}
				}
			}
		}
		return searchRAC.values();
	}

}

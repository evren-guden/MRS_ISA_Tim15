package rs.travel.bookingWithEase.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.RentACar;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.model.VehicleReservation;
import rs.travel.bookingWithEase.repository.IRACRepository;

@Service
public class RACService {

	@Autowired
	private IRACRepository rentacarsRepository;

	public RentACar findOne(Long id) {
		Optional<RentACar> rac = rentacarsRepository.findById(id);

		if (rac.isPresent()) {
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

	public Collection<RentACar> findByNameAndAddress(String name, String address, Date begin, Date end) {
		List<RentACar> racs = rentacarsRepository.findByNameContainingIgnoreCaseAndAddressContainingIgnoreCase(name,
				address);

		List<RentACar> available = new ArrayList<RentACar>();
		boolean find = false;
		
		for (RentACar rentACar : racs) {
			find = false;
			for (Vehicle veh : rentACar.getVehicles()) {
				if(veh.getVehicleReservations().size()==0) {
					find = true;
				}
				for(VehicleReservation res: veh.getVehicleReservations()) {
					if ((begin.before(res.getCheckInDate()) && end.after(res.getCheckOutDate())) || (begin.after(res.getCheckInDate()) && begin.before(res.getCheckOutDate()))|| (end.after(res.getCheckInDate()) && begin.before(res.getCheckOutDate()))) {
						find = false;
					}else {
						find = true;
						break;
					}
				}
				
				if(find) {
					available.add(rentACar);
					break;
				}
			}
		}

		return available;
	}

}

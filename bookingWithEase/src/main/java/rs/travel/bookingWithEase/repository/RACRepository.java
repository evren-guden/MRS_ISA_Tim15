package rs.travel.bookingWithEase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.dto.RentACarDTO;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.RentACar;

@Repository
public class RACRepository implements IRACRepository{
	
	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, RentACar> rac = new ConcurrentHashMap<Long, RentACar>();

	@Override
	public Collection<RentACar> findAll() {
		return this.rac.values();
	}

	@Override
	public RentACar create(Company company) {
		RentACar rentACar = new RentACar(company);
		Long id = rentACar.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			rentACar.setId(id);
		}
		this.rac.put(id, rentACar);

		return rentACar;
	}
	
	@Override
	public Collection<RentACar> search(RentACarDTO rentACar) {
		ConcurrentMap<Long, RentACar> searchRAC = new ConcurrentHashMap<Long, RentACar>();
		
		for (RentACar rac : this.rac.values()) {
			if(rentACar.getId() == null || rentACar.getId()==rac.getId()) {
				if(rentACar.getAddress() == null || rac.getAddress().toLowerCase().contains(rentACar.getAddress().toLowerCase())) {
					if(rentACar.getName() == null || rac.getName().toLowerCase().contains(rentACar.getName().toLowerCase())) {
						searchRAC.put(rac.getId(), rac);
					}
				}
			}
		}
		
		return searchRAC.values();
	}
}

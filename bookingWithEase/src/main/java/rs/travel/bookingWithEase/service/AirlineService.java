package rs.travel.bookingWithEase.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.AirlineDTO;
import rs.travel.bookingWithEase.model.Airline;
import rs.travel.bookingWithEase.repository.IAirlineRepository;

@Service
public class AirlineService {

	@Autowired
	private IAirlineRepository airlines;

	public Optional<Airline> findOne(Long id) {
		return airlines.findById(id);
	}

	public List<Airline> findAll() {
		return airlines.findAll();
	}

	public Airline save(Airline airline) {
		return airlines.save(airline);
	}

	public void delete(Long id) {
		airlines.deleteById(id);
	}

	public Airline findOneByName(String name) {
		return airlines.findOneByName(name);

	}
	
	public Collection<Airline> search(AirlineDTO airline) {
		ConcurrentMap<Long, Airline> searchAirlines = new ConcurrentHashMap<Long, Airline>();

		for (Airline al : airlines.findAll()) {
			if (airline.getId() == null || airline.getId() == al.getId()) {
				if (airline.getAddress() == null
						|| al.getAddress().toLowerCase().contains(airline.getAddress().toLowerCase())) {
					if (airline.getName() == null
							|| al.getName().toLowerCase().contains(airline.getName().toLowerCase())) {
						searchAirlines.put(al.getId(), al);
					}
				}
			}
		}
		
		return searchAirlines.values();
	}

	/*
	 * @Override public Collection<Airline> findAll() { return airlines.findAll(); }
	 * 
	 * @Override public Airline create(Company company) throws Exception { Airline
	 * airline = new Airline(company); if (airline.getId() != null) { throw new
	 * Exception(" "); }
	 * 
	 * return airlines.create(airline);
	 * 
	 * }
	 * 
	 * @Override public Airline find(Long id) { return airlines.find(id); }
	 * 
	 * 
	 * @Override public Airline update(Airline airline) throws Exception { Airline
	 * findedAirline = find(airline.getId());
	 * 
	 * if (findedAirline == null) { throw new
	 * Exception("Ne postoji vozilo servis sa tim identifikatorom."); }
	 * findedAirline.setId(airline.getId());
	 * findedAirline.setName(airline.getName());
	 * findedAirline.setAddress(airline.getAddress());
	 * findedAirline.setDescription(airline.getDescription());
	 * 
	 * return airlines.update(findedAirline); }
	 */

}
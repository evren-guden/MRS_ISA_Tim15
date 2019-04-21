package rs.travel.bookingWithEase.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Airline;

import rs.travel.bookingWithEase.model.Company;
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
	
	
/*	@Override
	public Collection<Airline> findAll()
	{
		return airlines.findAll();
	}
	
	@Override
	public Airline create(Company company) throws Exception
	{
		Airline airline = new Airline(company);
		if (airline.getId() != null) {
			throw new Exception(" ");
		}

		return airlines.create(airline);
		
	}
	
	@Override
	public Airline find(Long id) {
		return airlines.find(id);
	}

	
	@Override
	public Airline update(Airline airline) throws Exception {
		Airline findedAirline = find(airline.getId());

		if (findedAirline == null) {
			throw new Exception("Ne postoji vozilo servis sa tim identifikatorom.");
		}
		findedAirline.setId(airline.getId());
		findedAirline.setName(airline.getName());
		findedAirline.setAddress(airline.getAddress());
		findedAirline.setDescription(airline.getDescription());

		return airlines.update(findedAirline);
	}*/

}
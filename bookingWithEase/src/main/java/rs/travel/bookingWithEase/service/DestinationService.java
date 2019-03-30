package rs.travel.bookingWithEase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Destination;

import rs.travel.bookingWithEase.repository.DestinationRepository;

@Service
public class DestinationService implements IDestinationService {
	
	
	@Autowired
	private DestinationRepository destinations;

	@Override
	public Collection<Destination> findAll() {
		// TODO Auto-generated method stub
		return destinations.findAll();
	}

	@Override
	public Destination create(Destination d) throws Exception {
		Destination destination = new Destination(d);
		if (destination.getIdAerodromes() != null) {
			throw new Exception(" ");
		}

		return destinations.create(destination);
	}

	@Override
	public Destination find(Long id) {
		return destinations.find(id);
	}

	@Override
	public Destination update(Destination destination) throws Exception {
		Destination findedDestination = find(destination.getIdAerodromes());

		if (findedDestination == null) {
			throw new Exception("Ne postoji aerodrom tim id-om.");
		}
		findedDestination.setIdAerodromes(destination.getIdAerodromes());
		findedDestination.setNameAerodroms(destination.getNameAerodroms());
		findedDestination.setAddress(destination.getAddress());
		

		return destinations.update(findedDestination);
	}
	
}

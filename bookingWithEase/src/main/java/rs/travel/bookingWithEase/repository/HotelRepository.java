package rs.travel.bookingWithEase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.Hotel;

@Repository
public class HotelRepository implements IHotelRepository {
	
	private static AtomicLong counter = new AtomicLong();
	
	private final ConcurrentMap<Long,Hotel> hotels = new ConcurrentHashMap<Long,Hotel>();
	
	@Override
	public Collection<Hotel> findAll() {
		return this.hotels.values();
	}

	@Override
	public Hotel create(Hotel hotel) {
		Long id = hotel.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			hotel.setId(id);
		}
		this.hotels.put(id, hotel);

		return hotel;
	}

	@Override
	public Hotel find(Long id) {
		
		return this.hotels.get(id);
	}

	@Override
	public Hotel update(Hotel hotel) {
		
		return null;
	}

	@Override
	public void delete(Long id) {
		
	}

}

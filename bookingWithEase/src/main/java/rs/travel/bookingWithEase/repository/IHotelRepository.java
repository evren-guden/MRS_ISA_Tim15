package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.Hotel;

public interface IHotelRepository extends JpaRepository<Hotel, Long>{
	
	
}

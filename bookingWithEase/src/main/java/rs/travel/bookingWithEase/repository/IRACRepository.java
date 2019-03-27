package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.RentACar;

public interface IRACRepository extends JpaRepository<RentACar, Long> {
	
	
}

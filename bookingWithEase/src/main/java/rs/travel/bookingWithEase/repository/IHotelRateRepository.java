package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.travel.bookingWithEase.model.HotelRate;

public interface IHotelRateRepository extends JpaRepository<HotelRate, Long>{

	@Query("SELECT avg(rate) from HotelRate hr where hr.reservation.room.hotel.id = ?1")
	Double findAverageByCompany(Long id);
	
	@Query("SELECT avg(rate) from HotelRate hr where hr.reservation.room.id = ?1")
	Double findAverageByRoom(Long id);
}

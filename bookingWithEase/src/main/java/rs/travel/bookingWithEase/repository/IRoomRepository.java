package rs.travel.bookingWithEase.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.Room;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Long> {

	ArrayList<Room> findByHotel(Hotel hotel);

	@Query("SELECT r FROM Room r WHERE r.capacity = ?1 AND r.floorNumber = ?2"
			+ " AND ?4 >= r.pricePerNight AND ?3 <= r.pricePerNight")
	ArrayList<Room> search(int capacity, int floorNumber, double minPrice, double maxPrice);

	@Query("SELECT r FROM Room r WHERE r.capacity = ?1 AND ?3 >= r.pricePerNight AND ?2 <= r.pricePerNight")
	ArrayList<Room> findByPriceRangeAndCapacity(int capacity, double minPrice, double maxPrice);

	@Query("SELECT r FROM Room r WHERE r.floorNumber = ?1 AND ?3 >= r.pricePerNight AND ?2 <= r.pricePerNight")
	ArrayList<Room> findByPriceRangeAndFloorNumber(int floorNumber, double minPrice, double maxPrice);

	@Query("SELECT r FROM Room r WHERE ?2 >= r.pricePerNight AND ?1 <= r.pricePerNight")
	ArrayList<Room> findByPriceRange(double minPrice, double maxPrice);
}

package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.Room;

public interface IRoomRepository extends JpaRepository<Room, Long>{
	
	
}

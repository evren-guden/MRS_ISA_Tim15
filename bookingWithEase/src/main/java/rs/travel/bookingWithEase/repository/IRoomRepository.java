package rs.travel.bookingWithEase.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.travel.bookingWithEase.model.Hotel;
import rs.travel.bookingWithEase.model.Room;

public interface IRoomRepository extends JpaRepository<Room, Long> {

	ArrayList<Room> findByHotel(Hotel hotel);
}

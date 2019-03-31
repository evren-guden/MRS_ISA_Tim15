package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.Flight;


public interface IFlightRepository extends JpaRepository<Flight, Long>{

}

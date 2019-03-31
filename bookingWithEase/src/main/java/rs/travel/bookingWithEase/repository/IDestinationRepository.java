package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.Destination;



public interface IDestinationRepository extends JpaRepository<Destination, Long>{

}

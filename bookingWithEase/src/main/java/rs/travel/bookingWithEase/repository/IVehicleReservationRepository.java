package rs.travel.bookingWithEase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.travel.bookingWithEase.model.VehicleReservation;


public interface IVehicleReservationRepository extends JpaRepository<VehicleReservation, Long>{

}

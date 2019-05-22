package rs.travel.bookingWithEase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.QuickVehicleReservationDTO;
import rs.travel.bookingWithEase.model.QuickVehicleReservation;
import rs.travel.bookingWithEase.model.Vehicle;
import rs.travel.bookingWithEase.repository.IQuickVehicleReservationRepository;
import rs.travel.bookingWithEase.repository.IRACSpecialOfferRepository;

@Service
public class QuickVehicleReservationService {

	@Autowired
	private IQuickVehicleReservationRepository quickVehRep;

	@Autowired
	private IRACSpecialOfferRepository racSORepository;

	@Autowired
	private VehicleService vehicleService;

	public List<QuickVehicleReservation> getAll() {
		return quickVehRep.findAll();
	}

	public QuickVehicleReservation getOne(Long id) {

		Optional<QuickVehicleReservation> qvr = quickVehRep.findById(id);

		if (qvr.isPresent()) {
			return qvr.get();
		}

		return null;
	}

	public QuickVehicleReservation add(QuickVehicleReservation qvr) {

		QuickVehicleReservation qvr2 = null;

		if (qvr.getId() == null) {
			qvr2 = quickVehRep.save(qvr);
			return qvr2;
		}

		return null;
	}

	public QuickVehicleReservation update(QuickVehicleReservation qvr) {

		QuickVehicleReservation qvr2 = getOne(qvr.getId());

		if (qvr2 != null) {
			qvr2 = quickVehRep.save(qvr);
			return qvr;
		}

		return null;
	}

	public void delete(Long id) {
		QuickVehicleReservation qvr = getOne(id);

		if (qvr != null) {
			quickVehRep.deleteById(id);
		}

	}
	
	public List<QuickVehicleReservation> findByRac(Long racId){
		return quickVehRep.findByRentacar(racId);
	}

	public List<QuickVehicleReservation> dtoToReservations(QuickVehicleReservationDTO dto) {

		ArrayList<QuickVehicleReservation> reservations = new ArrayList<QuickVehicleReservation>();
		QuickVehicleReservation qvr = new QuickVehicleReservation();
		for (Long vehId : dto.getVehicles()) {
			// TODO Validation
			Vehicle v = vehicleService.findOne(vehId);
			// VehicleDTO vehicleDto = roomService.roomToDTO(r);
			// roomService.calculateTotalPrice(roomDto, dto.getCheckIn(),
			// dto.getCheckOut());

			qvr = new QuickVehicleReservation();
			qvr.setCheckInDate(dto.getCheckIn());
			qvr.setCheckOutDate(dto.getCheckOut());
			qvr.setVehicle(v);
			qvr.setDiscount(dto.getDiscount());
			// qvr = new QuickVehicleReservation(0l, v, null, dto.getCheckIn(),
			// dto.getCheckOut(), soSet, null, dto.getDiscount());
			reservations.add(qvr);
		}

		return reservations;
	}
}

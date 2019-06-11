package rs.travel.bookingWithEase.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.travel.bookingWithEase.dto.QuickVehicleReservationDTO;
import rs.travel.bookingWithEase.model.QuickVehicleReservation;
import rs.travel.bookingWithEase.service.QuickVehicleReservationService;

@Controller
@RequestMapping(value = "rentacars/{racId}/quickVehicleReservations")
public class QuickVehicleReservationController {

	@Autowired
	private QuickVehicleReservationService quickVehService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<QuickVehicleReservation>> getAll() {

		Collection<QuickVehicleReservation> qvr = quickVehService.getAll();

		return new ResponseEntity<Collection<QuickVehicleReservation>>(qvr, HttpStatus.OK);
	}
	
	/*@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@RequestBody QuickVehicleReservationDTO dto){
		
		List<QuickVehicleReservation> qvr = quickVehService.dtoToReservations(dto);
	
		for (QuickVehicleReservation quickVehicleReservation : qvr) {
			quickVehService.add(quickVehicleReservation);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}*/
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody QuickVehicleReservationDTO dto){
		
		List<QuickVehicleReservation> qvr = quickVehService.dtoToReservations(dto);
		
		for (QuickVehicleReservation quickVehicleReservation : qvr) {
			quickVehService.update(quickVehicleReservation);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

package rs.travel.bookingWithEase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.CompanyRateDTO;
import rs.travel.bookingWithEase.model.HotelRate;
import rs.travel.bookingWithEase.repository.IHotelRateRepository;
import rs.travel.bookingWithEase.repository.IRoomReservationRepository;

@Service
public class RoomRateService {
	
	@Autowired
	private IHotelRateRepository hotelRateRepository;

	@Autowired
	private IRoomReservationRepository roomResRepository;

	public HotelRate saveRate(CompanyRateDTO dto) {

		HotelRate hr = new HotelRate();
		hr.setRate(dto.getRate());
		hr.setReservation(roomResRepository.getOne(dto.getReservationId()));

		hr = hotelRateRepository.save(hr);

		return hr;

	}

}

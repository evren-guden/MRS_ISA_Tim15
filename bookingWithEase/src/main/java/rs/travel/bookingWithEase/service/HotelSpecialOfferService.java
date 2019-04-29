package rs.travel.bookingWithEase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.HotelSpecialOffer;
import rs.travel.bookingWithEase.repository.IHotelSpecialOfferRepository;

@Service
public class HotelSpecialOfferService {
	
	@Autowired
	IHotelSpecialOfferRepository specialOffers;

	public HotelSpecialOffer save(HotelSpecialOffer hotelSpecialOffer) {
		return specialOffers.save(hotelSpecialOffer);
	}
	
	
}

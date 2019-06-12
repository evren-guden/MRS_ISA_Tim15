package rs.travel.bookingWithEase.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class QuickVehicleReservation extends VehicleReservation {

	private Integer discount;
	private Double finalPrice;
	
	public QuickVehicleReservation() {
	}

	public Integer getDiscount() {
		return discount;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
}

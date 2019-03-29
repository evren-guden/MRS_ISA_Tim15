package rs.travel.bookingWithEase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAerodromes;

	@Column(name = "nameAerodroms")
	private String nameAerodroms;

	@Column(name = "address")
	private String address;

	public Long getIdAerodromes() {
		return idAerodromes;
	}

	public void setIdAerodromes(Long idAerodromes) {
		this.idAerodromes = idAerodromes;
	}

	public String getNameAerodroms() {
		return nameAerodroms;
	}

	public void setNameAerodroms(String nameAerodroms) {
		this.nameAerodroms = nameAerodroms;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Destination(Long idAerodromes, String nameAerodroms, String address) {
		super();
		this.idAerodromes = idAerodromes;
		this.nameAerodroms = nameAerodroms;
		this.address = address;
	}

}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((idAerodromes == null) ? 0 : idAerodromes.hashCode());
		result = prime * result + ((nameAerodroms == null) ? 0 : nameAerodroms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destination other = (Destination) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (idAerodromes == null) {
			if (other.idAerodromes != null)
				return false;
		} else if (!idAerodromes.equals(other.idAerodromes))
			return false;
		if (nameAerodroms == null) {
			if (other.nameAerodroms != null)
				return false;
		} else if (!nameAerodroms.equals(other.nameAerodroms))
			return false;
		return true;
	}

}
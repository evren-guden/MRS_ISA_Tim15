package rs.travel.bookingWithEase.model;

public class Destination {

	private String idAerodromes;
	private String nameAerodroms;
	private Location address;

	public Destination(String idAerodromes, String nameAerodroms, Location address) {
		super();
		this.idAerodromes = idAerodromes;
		this.nameAerodroms = nameAerodroms;
		this.address = address;
	}

	public String getIdAerodromes() {
		return idAerodromes;
	}

	public void setIdAerodromes(String idAerodromes) {
		this.idAerodromes = idAerodromes;
	}

	public String getNameAerodroms() {
		return nameAerodroms;
	}

	public void setNameAerodroms(String nameAerodroms) {
		this.nameAerodroms = nameAerodroms;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}
}

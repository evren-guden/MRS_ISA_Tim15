package rs.travel.bookingWithEase.dto;

public class CompanyDTO {
	
	private Long id;
	private String name;
	private String address;
	private String description;
	private String cmpType;
	
	public CompanyDTO() {
		super();
	}

	public CompanyDTO(Long id, String name, String address, String description, String cmpType) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.cmpType = cmpType;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCmpType() {
		return cmpType;
	}

	public void setCmpType(String cmpType) {
		this.cmpType = cmpType;
	}
	
}

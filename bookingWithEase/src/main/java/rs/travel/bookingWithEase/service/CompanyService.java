package rs.travel.bookingWithEase.service;

import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.model.Company;

@Service
public class CompanyService {

	public Company dtoToCompany(CompanyDTO companyDto) {
		return new Company(companyDto.getName(), companyDto.getAddress(), companyDto.getDescription());

	}

}

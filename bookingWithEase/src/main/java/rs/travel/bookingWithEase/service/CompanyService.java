package rs.travel.bookingWithEase.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.model.Admin;

@Service
public class CompanyService {

	@Autowired
	UserService userService;

	public Company dtoToCompany(CompanyDTO companyDto) {
		Set<Admin> admins = new HashSet<Admin>();
		if (companyDto.getAdmins() != null) {
			for (String adminUsername : companyDto.getAdmins()) {
				User a = userService.findByUsername(adminUsername);
				Admin admin = (Admin) a;
				if (admin != null) {
					admins.add(admin);
				}
			}
		}
		return new Company(companyDto.getId(), companyDto.getName(), companyDto.getAddress(),
				companyDto.getDescription(), admins);

	}

}

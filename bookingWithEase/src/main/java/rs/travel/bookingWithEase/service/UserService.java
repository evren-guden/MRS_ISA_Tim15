package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.AdminUserDTO;
import rs.travel.bookingWithEase.model.ADMIN_TYPE;
import rs.travel.bookingWithEase.model.Admin;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository users;

	public User findOne(Long id) {
		Optional<User> user = users.findById(id);

		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

	public List<User> findAll() {
		System.out.println("\n User service find all called \n");
		for (User u : users.findAll()) {
			System.out.println("\n   " + u.getUsername());
		}

		return users.findAll();
	}

	public boolean save(User user) {

		if (users.findByUsername(user.getUsername()) == null) {
			users.save(user);
			return true;
		} else {
			return false;
		}

	}

	public User update(User user) {
		return users.save(user);
	}

	public void delete(Long id) {
		users.deleteById(id);
	}

	public User dtoToUser(AdminUserDTO adminUserDto) {
		System.out.println("****************************** \n\n\n username: " + adminUserDto.getUsername() + " type: "
				+ adminUserDto.getType() + " company: " + adminUserDto.getCompanyId());
		Company company = new Company();

		return new Admin(null, adminUserDto.getUsername(), adminUserDto.getFirstName(), adminUserDto.getLastName(),
				adminUserDto.getEmail(), adminUserDto.getPassword(), "", "", "",
				ADMIN_TYPE.strToAdmin(adminUserDto.getType()), company);
	}

	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = users.findByUsername(username);
		return u;
	}

	public User findByEmail(String email) {
		User u = users.findByEmailIgnoreCase(email);
		return u;
	}
	
	
}

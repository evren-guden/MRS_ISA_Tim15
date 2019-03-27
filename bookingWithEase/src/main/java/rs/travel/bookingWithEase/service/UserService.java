package rs.travel.bookingWithEase.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.AdminUserDTO;
import rs.travel.bookingWithEase.dto.CompanyDTO;
import rs.travel.bookingWithEase.model.ADMIN_TYPE;
import rs.travel.bookingWithEase.model.Admin;
import rs.travel.bookingWithEase.model.Company;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository users;
	
	@Override
	public Collection<User> findAll() {
		return users.findAll();
	}

	@Override
	public User find(Long id) {
		return users.find(id);
	}

	@Override
	public User create(User user) throws Exception {
		
		if (user.getId() != null) {
			throw new Exception("");
		}

		return users.create(user);
	}
	
	public User dtoToUser(AdminUserDTO adminUserDto) {
		return new Admin(null,adminUserDto.getUsername(),adminUserDto.getFirstName(),adminUserDto.getLastName(),
				         adminUserDto.getEmail(),adminUserDto.getPassword(),"","","",
				         ADMIN_TYPE.strToAdmin(adminUserDto.getType()));
	}

}

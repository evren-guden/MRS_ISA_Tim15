package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.dto.AdminUserDTO;
import rs.travel.bookingWithEase.model.ADMIN_TYPE;
import rs.travel.bookingWithEase.model.Admin;
import rs.travel.bookingWithEase.model.User;
import rs.travel.bookingWithEase.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository users;
	
	public Optional<User> findOne(Long id) {
		return users.findById(id);
	}

	public List<User> findAll() {
		return users.findAll();
	}
	
	public boolean save(User user) {
		
		if(users.findByUsername(user.getUsername()) == null) {
			System.out.println(users.findByUsername(user.getUsername()));
            users.save(user);
            return true;
        }else {
        	return false;
        }
        
	}

	public void delete(Long id) {
		users.deleteById(id);
	}
	
	public User dtoToUser(AdminUserDTO adminUserDto) {
		return new Admin(null,adminUserDto.getUsername(),adminUserDto.getFirstName(),adminUserDto.getLastName(),
				         adminUserDto.getEmail(),adminUserDto.getPassword(),"","","",
				         ADMIN_TYPE.strToAdmin(adminUserDto.getType()));
	}
	
	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = users.findByUsername(username);
		return u;
	}

}

package rs.travel.bookingWithEase.repository;

import java.util.Collection;

import rs.travel.bookingWithEase.model.User;

public interface IUserRepository {
	
	Collection<User> findAll();

	User create(User user);

	User find(Long id);

}

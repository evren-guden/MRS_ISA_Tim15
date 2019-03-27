package rs.travel.bookingWithEase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.User;

@Repository
public class UserRepository implements IUserRepository{
	
	private static AtomicLong counter = new AtomicLong();
	
	private final ConcurrentMap<Long,User> users = new ConcurrentHashMap<Long,User>();
	
	@Override
	public Collection<User> findAll() {
		return this.users.values();
	}

	@Override
	public User create(User user) {
		Long id = user.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			user.setId(id);
		}
		this.users.put(id, user);

		return user;
	}

	@Override
	public User find(Long id) {
		
		return this.users.get(id);
	}
}

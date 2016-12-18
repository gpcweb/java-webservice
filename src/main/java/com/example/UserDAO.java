package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	
	private static final AtomicLong counter = new AtomicLong();

	// Dummy database. Initialize with some dummy values.
	private static List<User> users;
	{
		users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "usuario1", "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"));
		users.add(new User(counter.incrementAndGet(), "usuario1", "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"));
	}

	public List list() {
		return users;
	}

	public User get(Long id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public User create(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
		return user;
	}

}


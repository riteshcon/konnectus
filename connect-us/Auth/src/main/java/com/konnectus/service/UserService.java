package com.konnectus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.konnectus.domain.User;
import com.konnectus.repository.UserRepository;


@Service(value = "userService")
public class UserService {
	
	private final Logger _log = LoggerFactory.getLogger(getClass());
	private final UserRepository userRepository;
	
	public void createUser(User user) {
		_log.info("Saving user");
		userRepository.save(user);
	}
	public void createUsers(List<User> users) {
		_log.info("Saving all users");
		userRepository.saveAll(users);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findUserById(String userId) {
		return userRepository.findById(userId).get();
	}
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}

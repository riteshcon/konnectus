package com.konnectus.auth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konnectus.domain.User;
import com.konnectus.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private final Logger _log = LoggerFactory.getLogger(getClass());
	@Autowired UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		_log.info("Getting all users.");
		return userService.findAll();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		_log.info("Getting user with ID: {}.", userId);
		return userService.findUserById(userId);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addUsers(@RequestBody User user) {
		_log.info("Saving user.");
		userService.createUser(user);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public void updateUsers(@RequestBody User user) {
		_log.info("Saving user.");
		userService.createUser(user);
	}
	
	
}

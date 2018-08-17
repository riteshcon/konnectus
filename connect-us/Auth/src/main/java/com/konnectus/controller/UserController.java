package com.konnectus.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
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
import com.mongodb.client.MongoCursor;

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
	
	@RequestMapping(method = RequestMethod.POST)
	public void addUsers(@RequestBody User user) {
		_log.info("Saving user.");
		userService.createUser(user);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByHobby/{hobby}")
	public List<Document> findUserByHobby(@PathVariable String hobby) {
		_log.info("Entering controller - findUserByHobby");
		MongoCursor<Document> result = userService.findUserByHobby(hobby);
		
		List<Document> returnableResult = new ArrayList<Document>();
		while(result.hasNext())
			returnableResult.add(result.next());
		
		return returnableResult;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByHobbyExample/{hobby}")
	public User findUserByHobbyExample(@PathVariable String hobby) {
		_log.info("Entering controller - findUserByHobbyExample" );
		
		
		return userService.findUserByHobbyExample(hobby);
	}
		
	
}

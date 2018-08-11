package com.konnectus.service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.konnectus.domain.User;
import com.konnectus.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Service(value = "userService")
public class UserService {

	private final Logger _log = LoggerFactory.getLogger(getClass());
	@Autowired
	private final UserRepository userRepository;

	public void createUser(User user) {
		_log.info("Saving user");
		userRepository.save(user);
	}

	public void createUsers(List<User> users) {
		_log.info("Saving all users");
		userRepository.saveAll(users);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findUserById(String userId) {
		return userRepository.findById(userId).get();
	}

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Database service to search for the parameter String pattern within User hobbies. Hobbies are stored
	 * as a list for each user.
	 * This method will open and close its own connection to Query the DB.
	 * @param hobby
	 * @return
	 */
	public MongoCursor<org.bson.Document> findUserByHobby(String hobby) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("konnectus");
		MongoCollection<org.bson.Document> collection = database.getCollection("user");
		BasicDBObject regexQuery = new BasicDBObject();
		
	    regexQuery.put("hobbies",
	        new BasicDBObject("$regex", "/*"+hobby+"/*")); // /* is equivalent of % in SQL.

		MongoCursor<org.bson.Document> cursor = collection.find(regexQuery).iterator();

		mongoClient.close();
		return cursor;
	}
	
	/**
	 * Database service to search for the parameter String pattern within User hobbies. Hobbies are stored
	 * as a list for each user.
	 * This method will user connections and finders managed by Spring/JPA. More specifically it 
	 * will do a search by example by passing an example of what it wants, to JPA.
	 * @param hobby
	 * @return
	 */
	public User findUserByHobbyExample(String hobby) {

		ExampleMatcher matcher =
	            ExampleMatcher.matchingAny().withMatcher("hobbies", contains().ignoreCase()).withIncludeNullValues()
	            .withIgnorePaths("id","name","email","age","address","audit","mobile");
		
		User searchUser = new User();
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add(hobby);
		searchUser.setHobbies(hobbyList);
		
		Example<User> searchExample = Example.of(searchUser, matcher);
		return userRepository.findOne(searchExample).get();
	}
}

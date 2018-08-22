package com.konnectus.service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.konnectus.domain.Event;
import com.konnectus.domain.User;
import com.konnectus.repository.EventRepository;


@Service
public class EventService {
	
	private final Logger _log = LoggerFactory.getLogger(getClass());
	@Autowired
	private EventRepository eventRepository;
	
	public Event addEvent(Event event) {
		_log.info("creating new event");
		return eventRepository.save(event);
	}
	
	public Event addUserToEvent(String eventId, String userId) {
		Optional<Event> eventOptionalToSave = eventRepository.findById(eventId);
		User eventUser = new User(userId);
		
		if(eventOptionalToSave.isPresent()) {
			Event eventToSave = eventOptionalToSave.get();
			List<User> users = eventToSave.getUsers();
			if(users == null) {
				users = new ArrayList<User>();
			}
			users.add(eventUser);
			eventToSave.setUsers(users);
			return eventRepository.save(eventToSave);
		}
		return null;
	}
	
	public List<Event> getEvents() {
		return eventRepository.findAll();
	}
	
	public Event findEventByName(String name) {
		ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("eventName", contains().ignoreCase());
				

		Event searchEvent = new Event();
		searchEvent.setEventName(name);

		Example<Event> searchExample = Example.of(searchEvent, matcher);
		return eventRepository.findOne(searchExample).get();
	}
}

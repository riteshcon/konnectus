package com.konnectus.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Event addUserToEvent(String eventid, User user) {
		Optional<Event> eventOptionalToSave = eventRepository.findById(eventid);
		if(eventOptionalToSave.isPresent()) {
			Event eventToSave = eventOptionalToSave.get();
			Set<User> users = eventToSave.getUsers();
			if(users == null) {
				users = new HashSet<>();
			}
			users.add(user);
			return eventRepository.save(eventToSave);
		}
		return null;
	}
	
	public List<Event> getEvents() {
		return eventRepository.findAll();
	}
}

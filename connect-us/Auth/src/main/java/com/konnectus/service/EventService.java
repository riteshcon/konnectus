package com.konnectus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konnectus.domain.Event;
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
	
	public List<Event> getEvents() {
		return eventRepository.findAll();
	}
}

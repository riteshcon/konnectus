package com.konnectus.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konnectus.domain.Event;
import com.konnectus.service.EventService;

@RestController
@RequestMapping(value = "/event")
public class EventController {
	@Autowired
	private EventService eventService;
	
	private final Logger _log = LoggerFactory.getLogger(getClass());

	@RequestMapping(method= RequestMethod.POST)
	public Event addEvent(@RequestBody Event event) {
		return eventService.addEvent(event);
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public List<Event> getEvents() {
		return eventService.getEvents();
	}
	
	@RequestMapping(value = "/{eventId}/{userId}", method= RequestMethod.POST)
	public Event addUserToEvent(@PathVariable("eventId") String eventId, @PathVariable("userId") String userId) {
		return eventService.addUserToEvent(eventId, userId);
	}
	
	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
	public Event getEventByName(@PathVariable String name) {
		_log.info("Finding event by name");
		return eventService.findEventByName(name);
	}
}

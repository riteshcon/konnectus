package com.konnectus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.konnectus.domain.Event;
import com.konnectus.service.EventService;

@RestController
public class EventController {
	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/event", method= RequestMethod.POST)
	public Event addEvent(@RequestBody Event event) {
		return eventService.addEvent(event);
	}
	
	@RequestMapping(value = "/event", method= RequestMethod.GET)
	public List<Event> getEvents() {
		return eventService.getEvents();
	}
}

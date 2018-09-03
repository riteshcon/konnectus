package com.konnectus.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {

	
	@RequestMapping(value="/test/hello", method = RequestMethod.POST)
	public String hello() {
		return "hello";
	}
}

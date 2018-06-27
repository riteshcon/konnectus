package com.konnectus.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {

	@RequestMapping("/test/hello")
	public String hello() {
		return "hello";
	}
}

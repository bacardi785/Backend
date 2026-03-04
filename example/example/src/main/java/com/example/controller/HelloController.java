package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

	@PostMapping("/hello")
	public String helloPost(@RequestBody String name) {
		return name;
	}

}

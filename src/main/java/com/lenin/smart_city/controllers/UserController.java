package com.lenin.smart_city.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lenin.smart_city.data.repository.UserRepository;
import com.lenin.smart_city.models.auth.User;

@RestController
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path="")
	public @ResponseBody Iterable<User> index() {
		return userRepository.findAll();
	}

	@GetMapping(path="/roles")
	public @ResponseBody Iterable<User> roles() {
		return userRepository.findAll();
	}
	

}

package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Model.User;
import com.ecommerce.service.UserService;

@RestController
public class RegistrationController {

	@Autowired
	private UserService srv;
	
	@PostMapping("/registerUser")
	private void registerUser(@RequestBody User user) {
		// TODO Auto-generated method stub
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
		srv.addUser(user);
	}
	
}

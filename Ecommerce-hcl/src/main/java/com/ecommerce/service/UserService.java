package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.User;
import com.ecommerce.repo.UserRepository;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRep;
	
	public void addUser(User user) {
	
		userRep.save(user);
	}
}

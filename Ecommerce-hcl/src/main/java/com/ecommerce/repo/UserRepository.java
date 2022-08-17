package com.ecommerce.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Model.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String string);
}
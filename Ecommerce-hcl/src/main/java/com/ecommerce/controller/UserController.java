package com.ecommerce.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Model.Color;
import com.ecommerce.Model.Product;
import com.ecommerce.Model.ProductCategory;
import com.ecommerce.Model.Roles;
import com.ecommerce.Model.User;
import com.ecommerce.d.LoginD;
import com.ecommerce.d.RegisterD;
import com.ecommerce.repo.RoleRepository;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private ProductService prdservice;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterD registerD) {
		
		if(userRepository.existsByEmail(registerD.getEmail())) {
			return new ResponseEntity<>("Email is already registered", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setFirstName(registerD.getFirstName());
		user.setLastName(registerD.getLastName());
		user.setEmail(registerD.getEmail());
		user.setPhone(registerD.getPhone());
		user.setPermissions(registerD.getPermissions());
		user.setPassword(passwordEncoder.encode(registerD.getPassword()));
		
		Roles roles = roleRepository.findByRoleName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));
		
		userRepository.save(user);
		
		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginD loginD) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginD.getEmail(), loginD.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
	}
	
	@GetMapping("/admim/loadUsers")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> loadUsers(){
		return userRepository.findAll();
	}
	@PostMapping("/admin/addProduct")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void addProduct(@RequestBody Product prd) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.getCategoryID();
		productCategory.getCategoryName();
		
		Color color = new Color();
		color.getColorID();
		color.getColorName();
		
		prd.setColor(color);
		prd.setProductCategory(productCategory);
		prdservice.addProduct(prd);
	}
	
	@DeleteMapping("/admin/deleteProduct/{prodId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteProduct(@PathVariable Integer prodId) {
		prdservice.deleteProduct(prodId);
	}
	
	@PutMapping("/admin/updateProduct/{prodId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void updateProduct(@RequestBody Product prod, @PathVariable Integer prodId) {
		prdservice.updateProduct(prod, prodId);
	}
	
}
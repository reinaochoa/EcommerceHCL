package com.ecommerce.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="user")
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserID")
	private int userID;
	
	private String email, password;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	private String phone, permissions;
	
	@OneToMany(mappedBy= "user")
	private List<UserRoles> userRoles;
	
	@OneToMany(mappedBy= "user")
	private List<Payment> payment;
	
	@OneToMany(mappedBy= "user")
	private List<Address> address;
	
	@OneToMany(mappedBy= "user")
	private List<Orders> orders;

}
 
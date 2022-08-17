package com.ecommerce.Model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="userroles", 
	joinColumns = @JoinColumn(name = "UserID", referencedColumnName="UserID"),
	inverseJoinColumns = @JoinColumn(name="RoleID", referencedColumnName="RoleID"))
	private Set<Roles> roles;
	
	
	@OneToMany(mappedBy= "user")
	private List<Payment> payment;
	
	@OneToMany(mappedBy= "user")
	private List<Address> address;
	
	@OneToMany(mappedBy= "user")
	private List<Orders> orders;
	
	public User(String email, String password, String firstName,String lastName, String phone, String permissions) {
		
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.permissions = permissions;
		
		
	}



}
 
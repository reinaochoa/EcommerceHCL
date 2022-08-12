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
@Entity @Table(name="color")
public class Color {

	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="ColorID")
	private int colorID;
	
	@Column(name="colorname")
	private String ColorName;
	
	@OneToMany(mappedBy="color")
	private List<Product> product;
}

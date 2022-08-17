package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

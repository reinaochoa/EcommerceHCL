package com.ecommerce.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Model.Product;
import com.ecommerce.repo.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
	
	public void addProduct(Product prod) {
		productRepository.save(prod);
	}
	
	public boolean updateProduct(Product prod, Integer id) {
		Optional<Product> tempProd = productRepository.findById(id);
		boolean retVal = false;
		
		if(tempProd.isPresent()) {
			Product prd = tempProd.get();
			prd.setProductName(prod.getProductName());
			prd.setProductPrice(prod.getProductPrice());
			prd.setQOnHand(prod.getQOnHand());
			productRepository.save(prd);
			retVal = true;
		}
		return retVal;
	}
}
package com.gcu.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.models.ProductModel;
import com.gcu.services.OrdersBusinessServiceInterface;

@RestController
@RequestMapping("/api/products")
public class ProductsRestController {
	
	private final OrdersBusinessServiceInterface service;
	public ProductsRestController(OrdersBusinessServiceInterface service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public List<ProductModel> getAllProducts() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public ProductModel getProductById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping("{id}/delete")
	public boolean deleteProductById(@PathVariable("id") Long id) {
		return service.delete(id);
	}
	@PostMapping
	public boolean addProduct(@RequestBody ProductModel product) {
		boolean success = service.addOne(product);
		if(success) {
			return true;
		}
		else {
			return false;
			}
		}
	@PutMapping("{id}/update")
	public boolean updateProduct(@PathVariable Long id, @RequestBody ProductModel product) {
		return service.update(id, product);
	}
	
	
	
	
	

}

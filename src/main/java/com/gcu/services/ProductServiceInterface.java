package com.gcu.services;

import java.util.List;

import com.gcu.models.ProductModel;

public interface ProductServiceInterface {
	
	public ProductModel addProduct(String name, String description, double price, int quantity);
	public void deleteProduct(Long id);
	public List<ProductModel> getAllProducts();
	public ProductModel getProductById(Long id);
	public void updateProduct(Long id, String name, String description, double price, int quantity);
	public List<ProductModel> searchProducts(String keyword);
	
	
}

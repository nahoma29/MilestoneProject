package com.gcu.services;

import java.util.List;

import com.gcu.models.ProductModel;

public interface OrdersBusinessServiceInterface {
	
	public List<ProductModel> findAll();
    public ProductModel findById(Long id);
    public boolean addOne(ProductModel  model);
    public boolean update(Long id,ProductModel model);
    public boolean delete(Long id);
    public List<ProductModel> searchProduct(String searchTerm);
    
}

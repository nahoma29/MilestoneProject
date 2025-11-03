package com.gcu.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.gcu.data.OrdersDataInterface;
import com.gcu.models.ProductModel;

@Service
public class OrdersBusinessService  implements OrdersBusinessServiceInterface {
	
	
	private final OrdersDataInterface<ProductModel> dataService;
	
	public OrdersBusinessService(OrdersDataInterface<ProductModel> dataService) {
		this.dataService = dataService;
	}

	@Override
	public List<ProductModel> findAll() {
		
		return dataService.findAll();
	}

	@Override
	public ProductModel findById(Long id) {
		
		return dataService.findById(id);
	}

	@Override
	public boolean addOne(ProductModel model) {
		
		return dataService.addOne(model);
	}

	@Override
	public boolean update(Long id, ProductModel model) {
		
		return dataService.updateById(id, model);
	}

	@Override
	public boolean delete(Long id) {
		
		return dataService.deleteOne(id);
	}

	@Override
	public List<ProductModel> searchProduct(String searchTerm) {
		
		return dataService.searchOrders(searchTerm);
	}
	

}

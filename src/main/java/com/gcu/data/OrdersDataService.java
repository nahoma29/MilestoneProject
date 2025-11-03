package com.gcu.data;

import java.util.ArrayList;
import java.util.List;



import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

import com.gcu.models.OrderEntity;
import com.gcu.models.ProductModel;

@Repository
public class OrdersDataService implements OrdersDataInterface<ProductModel> {
	

	private final OrdersRepositoryInterface ordersRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	
	public OrdersDataService(OrdersRepositoryInterface ordersRepository) {
		this.ordersRepository = ordersRepository;
	}
	@Override
	public List<ProductModel> findAll() {
		Iterable<OrderEntity> ordersEntity = ordersRepository.findAll();
		List<ProductModel> models = new ArrayList<>();
		for(OrderEntity item: ordersEntity) {
			models.add(modelMapper.map(item, ProductModel.class));
		}
		return models;	
	}

	@Override
	public ProductModel findById(Long id) {
		OrderEntity entity = ordersRepository.findById(id).orElse(null);
		
		ProductModel model = modelMapper.map(entity, ProductModel.class);
		return model;
	}
	
	@Override
	public List<ProductModel> searchOrders(String searchTerm) {
		Iterable<OrderEntity> ordersEntity = ordersRepository.findByDescriptionContainingIgnoreCase(searchTerm);
		List<ProductModel> orders = new ArrayList<>();
		for(OrderEntity item: ordersEntity) {
			orders.add(modelMapper.map(item, ProductModel.class));
		}
		return orders;
	}
	
	@Override
	public boolean addOne(ProductModel newOrder) {
		OrderEntity entity = modelMapper.map(newOrder, OrderEntity.class);
		OrderEntity result = ordersRepository.save(entity);
		if(result != null && result.getId() != null) {
			return true;
		}else {
			return false;
		}

	}
	
	@Override
	public boolean updateById(Long id, ProductModel updateOrder) {
		if(!ordersRepository.existsById(id)) {
			return false;
		}
		OrderEntity entity = modelMapper.map(updateOrder, OrderEntity.class);
		entity.setId(id);
		ordersRepository.save(entity);
		return true;
	}
	
	@Override
	public boolean deleteOne(Long id) {
		if(!ordersRepository.existsById(id)) {
			return false;
		}
		ordersRepository.deleteById(id);
		return true;
	}


	


}

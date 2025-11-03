package com.gcu.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gcu.models.OrderEntity;

public interface OrdersRepositoryInterface extends CrudRepository<OrderEntity, Long> {

	List<OrderEntity> findByDescriptionContainingIgnoreCase(String searchTerm);
	
}

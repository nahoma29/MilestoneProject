package com.gcu.data;

import java.util.List;

public interface OrdersDataInterface <T>{
	
	public List<T> findAll();
    public T findById(Long id);
    public List<T> searchOrders(String searchTerm);
    public boolean addOne(T t);
    public boolean updateById(Long id, T t);
    public boolean deleteOne(Long id);
    
	
	
}

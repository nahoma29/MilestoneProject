package com.gcu.data;

public interface UsersDataInterface <T>{
	//  to be completed
	public T findByUsername(String username);
	public boolean save(T t);
	
}

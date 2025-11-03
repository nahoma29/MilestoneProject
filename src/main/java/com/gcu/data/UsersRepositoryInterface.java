package com.gcu.data;

import org.springframework.data.repository.CrudRepository;

import com.gcu.models.UserEntity;

public interface UsersRepositoryInterface extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByUsernameIgnoreCase(String username);
	

}

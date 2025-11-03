package com.gcu.data;



import org.springframework.stereotype.Service;

import com.gcu.models.UserEntity;

@Service
public class UsersDataService implements UsersDataInterface<UserEntity> {

	private final UsersRepositoryInterface usersRepository;
	
	public UsersDataService(UsersRepositoryInterface usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Override
	public UserEntity findByUsername(String username) {
		return usersRepository.findByUsernameIgnoreCase(username);
	}

	@Override
	public boolean save(UserEntity user) {
		try {
			usersRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

}

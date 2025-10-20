package com.gcu.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceInterface {
	
	public boolean authenticate(String username, String password) {
		if(!username.isEmpty() && !password.isEmpty()) {
			return true;
		}
		return false;
		
	}
	
}

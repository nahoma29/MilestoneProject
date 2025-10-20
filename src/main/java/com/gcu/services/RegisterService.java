package com.gcu.services;

import org.springframework.stereotype.Service;

@Service
public class RegisterService implements RegisterServiceInterface {
	
	public boolean register(String firstName, String lastName, String username, String password) {
		// Simple registration logic (for demonstration purposes)
		if(!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
			// In a real application, you would save the user details to a database here
			return true;
		}
		return false;
		
	}
	
	
}

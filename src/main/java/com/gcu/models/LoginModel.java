package com.gcu.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginModel {
	@NotNull(message="Username is a required field.")
	@Size(min=3, max=15, message="Username must be between 3 and 15 characters.")
	private String username;
	@NotNull(message="Password is a required field.")
	@Size(min=8, max=15, message="Password must be between 8 and 15 characters.")
	private String password;
	
	public LoginModel(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public LoginModel() {
		
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

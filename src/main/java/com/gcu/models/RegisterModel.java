package com.gcu.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterModel {
	@NotNull(message="First name is a required field.")
	@Size(min=3, max=15, message="First name must be between 3 and 15 characters.")
	private String firstName;
	@NotNull(message="Last name is a required field.")
	@Size(min=3, max=15, message="Last name must be between 3 and 15 characters.")
	private String lastName;
	@NotNull(message="Username is a required field.")
	@Size(min=3, max=15, message="Username must be between 3 and 15 characters.")
	private String username;
	@NotNull(message="Password is a required field.")
	@Size(min=8, max=15, message="Password must be between 8 and 15 characters.")
	private String password;
	
	public RegisterModel() {
		
	}
	
	public RegisterModel(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String toString() {
		return "First Name: " + firstName + ", Last Name: " + lastName + ", Username: " + username + ", Password: " + password;
	}
}

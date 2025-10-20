package com.gcu.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.LoginModel;

import com.gcu.services.LoginServiceInterface;

import jakarta.validation.Valid;

/**
 * Handles login and logout requests.
 * Uses simple server-side validation and a session attribute to represent a logged-in user.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	private final LoginServiceInterface loginService;
	/**
	 * Constructor injecting the LoginService.
	 * @param service the login service to use for authentication
	 */

	public LoginController(LoginServiceInterface loginService) {
		this.loginService = loginService;
		
	}
	/**
	 * Displays the login form.
	 * Adds a fresh LoginModel to the model.
	 * @param model the view model to receive attributes
	 * @return the login view name (e.g., "login")
	 */
	@GetMapping("/")
	public String display(Model model) {
		
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	/**
	 * Processes the submitted login form.
	 * Validates input and checks credentials; on success stores the current user in session.
	 * @param form the submitted login form data
	 * @param bindingResult validation result for the form
	 * @param session HTTP session used to store the current user
	 * @param model the view model for error messages
	 * @return redirect to "/account" on success; the login view on failure
	 */
	@PostMapping("/processLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}
		loginService.authenticate(loginModel.getUsername(), loginModel.getPassword());
		if(!loginService.authenticate(loginModel.getUsername(), loginModel.getPassword())) {
			bindingResult.reject("loginError, Invalid Credentials");
			model.addAttribute("title", "Login Form");
			model.addAttribute("loginError", "Invalid Credentials");
			return "login";
		}
		
	    model.addAttribute("loginModel", loginModel);
		return "loginResults";
		
	}
	/**
	 * Logs the user out by invalidating the session.
	 * @param session the HTTP session to invalidate
	 * @return redirect to the login page
	 */
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login/";
	}
	
	
	
}

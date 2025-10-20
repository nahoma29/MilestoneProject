package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.RegisterModel;
import com.gcu.services.RegisterServiceInterface;

import jakarta.validation.Valid;

/**
 * Handles user registration (show form and process submission).
 * No database is used in this milestone; behavior is demo-only.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private final RegisterServiceInterface registerService;
	
	public RegisterController(RegisterServiceInterface registerService) {
		this.registerService = registerService;
	}
	
	/**
	 * Displays the registration form.
	 * Adds a fresh RegisterModel to the model.
	 * @param model the view model to receive attributes
	 * @return the register view name (e.g., "register")
	 */
	@GetMapping("/")
	public String display(Model model) {
		
		model.addAttribute("title", "Register Form");
		model.addAttribute("registerModel", new RegisterModel());
		
		return "register";
	}
	
	@PostMapping("/processRegister")
	public String doRegister(@Valid RegisterModel registerModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Register Form");
			return "register";
		}
		boolean registered = registerService.register(registerModel.getFirstName(), registerModel.getLastName(), registerModel.getUsername(), registerModel.getPassword());
		if(!registered) {
			bindingResult.reject("registrationError", "Registration failed. Please try again.");
			model.addAttribute("title", "Register Form");
			model.addAttribute("registrationError", "Registration failed. Please try again.");
			return "register";
		}
		
			
		
		
	    
		return "redirect:/login/";
	}
}

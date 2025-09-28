package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.RegisterModel;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@GetMapping("/")
	public String display(Model model) {
		
		model.addAttribute("title", "Register Form");
		model.addAttribute("registerModel", new RegisterModel());
		
		return "register";
	}
	@PostMapping("/processRegister")
	public String doRegister(@Valid RegisterModel registerModel, BindingResult bindngResult, Model model) {
		if(bindngResult.hasErrors()) {
			model.addAttribute("title", "Register Form");
			return "register";
		}
		
	    
		return "redirect:/login/";
	}
}

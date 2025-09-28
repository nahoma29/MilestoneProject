package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.LoginModel;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/")
	public String display(Model model) {
		
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	@PostMapping("/processLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindngResult, Model model) {
		if(bindngResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}
		
	    model.addAttribute("loginModel", loginModel);
		return "loginResults.html";
	}
	
	
	
}

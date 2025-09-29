package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/** 
 * Handles requests for the application home page.
 * Renders the landing view and (optionally) redirects "/" to "/home".
 */
@Controller
public class HomeController {
	
	/** 
	 * Renders the home page.
	 * @param model Spring UI model to supply page attributes
	 * @return the view name for the home page (e.g., "index")
	 */
	@GetMapping("/")
	public String display(Model model) {
		model.addAttribute("title", "Home Page");
		return "index";
	}
	
}

package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.models.ProductModel;
import com.gcu.services.OrdersBusinessServiceInterface;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final OrdersBusinessServiceInterface service;
	
	public ProductController(OrdersBusinessServiceInterface service) {
		this.service = service;
	}

	@GetMapping("/")
	public String allProducts(Model model) {
		model.addAttribute("title", "Product List");
		model.addAttribute("products", service.findAll());
		return "productList";
	}
	@GetMapping("/new")
	public String newProduct(Model model) {
		model.addAttribute("title", "New Product Form");
		model.addAttribute("product", new ProductModel());
		return "productForm";
	}
	
	@PostMapping("/add")
	public String addProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "New Product Form");
			return "productForm";
		}
		service.addOne(productModel);

        
        return "redirect:/products/";
	}
	@PostMapping("/{id}/edit")
	public String updateProduct(@PathVariable Long id,
	                            @Valid ProductModel productModel,
	                            BindingResult bindingResult,
	                            Model model,
	                            RedirectAttributes ra) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("title", "Edit Product");
	        return "productForm";
	    }
	    boolean ok = service.update(id, productModel); // calls updateById under the hood
	    ra.addFlashAttribute(ok ? "success" : "error",
	                         ok ? "Product updated." : "Update failed or product not found.");
	    return "redirect:/products/";
	}
	@GetMapping("/{id}/delete")
	public String deleteProduct(@PathVariable Long id) {
		service.delete(id);
		
		return "redirect:/products/";
	}
	

}

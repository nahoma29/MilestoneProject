package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.ProductModel;
import com.gcu.services.ProductServiceInterface;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductServiceInterface productService;
	
	public ProductController(ProductServiceInterface productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public String allProducts(Model model) {
		model.addAttribute("title", "Product List");
		model.addAttribute("products", productService.getAllProducts());
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
		productService.addProduct(
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getQuantity()
        );

        
        return "redirect:/products/";
    
	}

}

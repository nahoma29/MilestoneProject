package com.gcu.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductModel {
	
	private Long id;

	@NotNull(message="Name is required")
	@Size(min = 3, max = 60, message = "Name must be between 3 and 20 characters")
	private String name;
	@NotNull(message="Description is required")
	@Size(min = 3, max = 255, message = "Name must be between 3 and 20 characters")
	private String description;
	@NotNull(message="Price is required")
	@Positive(message= "Price must be greater then 0")
	private double price;
	@NotNull(message="Quantity is required")
	@Min(value = 0, message="Quantity must be >= 0")
	private int quantity;
	
	public ProductModel() {
	}
	public ProductModel(Long id, String name, String description, double price, int quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
}

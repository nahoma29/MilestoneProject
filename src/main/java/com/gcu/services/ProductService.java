package com.gcu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.models.ProductModel;

@Service
public class ProductService implements ProductServiceInterface {
	
	private List<ProductModel> products = new ArrayList<>();
	private Long nextId = 1L;
	public ProductService() {
		initialize();
	}
	public void initialize() {
		products.add(new ProductModel(nextId++, "shoes", "Nike Air Force", 99, 5));
		products.add(new ProductModel(nextId++, "T-Shirt", "Old school Tee", 20, 15));
		products.add(new ProductModel(nextId++, "Jeans", "Cargo Jeans", 20, 15));
	}
	@Override
	public List<ProductModel> getAllProducts() {
		if(products.isEmpty()) {
			System.out.println("The inventory is Empty");
		}
		return products;
	}
	
	@Override
	public ProductModel addProduct(String name, String description, double price, int quantity) {
		
		Long id = nextId++;
		ProductModel newProduct = new ProductModel(id, name, description, price, quantity);
		products.add(newProduct);
		System.out.println("Product added: " + name + ", " + description + ", " + price + ", " + quantity);
		return newProduct;
	}

    @Override
    public void deleteProduct(Long id) {
        ProductModel toRemove = null;
        for (ProductModel p : products) {
            if (p.getId().equals(id)) {
                toRemove = p;
                break;
            }
        }

        if (toRemove != null) {
            products.remove(toRemove);
            System.out.println("Product deleted with ID: " + id);
        } else {
            System.out.println("Product with ID: " + id + " not found.");
        }
    }

	
	@Override
	public ProductModel getProductById(Long id) {
		for(ProductModel p: products) {
			if(p.getId().equals(id)) {
				return p;
			}
		}
		System.out.println("Product with ID: "+ id + " is not in the inventory");
		return null;
		
	}

	@Override
	public void updateProduct(Long id, String name, String description, double price, int quantity) {
		ProductModel product = getProductById(id);
		if(product != null) {
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setQuantity(quantity);
			System.out.println("Product with ID: " + id + " updated in the inventory" );
		}
		else {
			System.out.println("Product with ID: " + id + " is not found in the inventory" );
		}
		
	}

	@Override
	public List<ProductModel> searchProducts(String keyword) {
		List<ProductModel> matchingProducts = new ArrayList<>();
		
		for(ProductModel p: products) {
			if(p.getDescription().toLowerCase().contains(keyword.toLowerCase()) || p.getName().toLowerCase().contains(keyword.toLowerCase())) {
				matchingProducts.add(p);
			}
		}
		return matchingProducts;
	}

}

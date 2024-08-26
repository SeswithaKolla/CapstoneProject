package com.asc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asc.dto.Product;
import com.asc.service.ProductService;

@RestController
@RequestMapping("/api/shop/products")
public class ProductController {
	@Autowired
	private ProductService productImpl;
	@PostMapping("/addProduct")
	public Product addproduct(@RequestBody Product product) {
		return productImpl.save(product);
	}
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		return productImpl.save(product);
	}
	@DeleteMapping("/deleteById/{pId}")
	public void deleteById(@PathVariable("pId")int pId) {
		productImpl.deleteById(pId);
	}
	@GetMapping("/findAll")
	public List<Product> findAll(){
		return productImpl.findAll();
	}
	@GetMapping("/findById/{pId}")
	public Product findById(@PathVariable("pId")int pId){
		return productImpl.findById(pId);
	}
	@GetMapping("/findByName/{pName}")
	public List<Product> findByName(@PathVariable("pName") String pName){
		return productImpl.findBypName(pName);
	}
	@GetMapping("/findByCategory/{category}")
	public List<Product> findByCategory(@PathVariable("category") String category){
		return productImpl.findBycategory(category);
	}
}

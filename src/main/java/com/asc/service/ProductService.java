package com.asc.service;

import java.util.List;

import com.asc.dto.Product;

public interface ProductService {
	public Product save(Product product);
	public Product updateProdcut(Product product);
	public void deleteById(int pId);
	public List<Product> findAll();
	public Product findById(int pId);
	public List<Product> findBypName(String pName);
	public List<Product> findBycategory(String category);
}

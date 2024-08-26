package com.asc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asc.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	public Product findById(int pId);
	public List<Product> findBypName(String pName);
	public List<Product> findBycategory(String category);
}

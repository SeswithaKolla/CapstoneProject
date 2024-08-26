package com.asc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asc.dto.Product;
import com.asc.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepo.save(product);
	}

	@Override
	public Product updateProdcut(Product product) {
		// TODO Auto-generated method stub
		return productRepo.save(product);
	}

	@Override
	public void deleteById(int pId) {
		// TODO Auto-generated method stub
		productRepo.deleteById(pId);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product findById(int pId) {
		// TODO Auto-generated method stub
		return productRepo.findById(pId);
	}

	@Override
	public List<Product> findBypName(String pName) {
		// TODO Auto-generated method stub
		return productRepo.findBypName(pName);
	}

	@Override
	public List<Product> findBycategory(String category) {
		// TODO Auto-generated method stub
		return productRepo.findBycategory(category);
	}

}

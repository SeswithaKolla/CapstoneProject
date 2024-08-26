package com.asc.service;

import java.util.List;

import com.asc.dto.Customer;

public interface CustomerService {
	public Customer save(Customer customer);
	public Customer Update(Customer customer);
	public List<Customer> findAll();
	public void deleteById(int id);
	public Customer findById(int id);
	public List<Customer> findBycName(String cName);
	public List<Customer> findByCity(String city);
	public List<Customer> findBypinCode(int pincode);
}

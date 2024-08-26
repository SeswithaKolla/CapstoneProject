package com.asc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asc.dto.Customer;
import com.asc.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Customer Update(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		customerRepo.deleteById(id);
	}

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return customerRepo.findById(id);
	}

	@Override
	public List<Customer> findBycName(String cName) {
		// TODO Auto-generated method stub
		return customerRepo.findBycName(cName);
	}

	@Override
	public List<Customer> findByCity(String city) {
		// TODO Auto-generated method stub
		return customerRepo.findByCity(city);
	}

	@Override
	public List<Customer> findBypinCode(int pincode) {
		// TODO Auto-generated method stub
		return customerRepo.findBypinCode(pincode);
	}

	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.save(customer);
	}

}

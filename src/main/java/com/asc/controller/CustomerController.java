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

import com.asc.dto.Customer;
import com.asc.service.CustomerService;
import com.asc.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api/shop/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerImpl;
	@PostMapping("/add")
	public Customer save(@RequestBody Customer customer) {
		return customerImpl.save(customer);
	}
	@PutMapping("/update")
	public Customer update(@RequestBody Customer customer) {
		return customerImpl.save(customer);
	}
	@GetMapping("/displayAll")
	public List<Customer> findAll(){
		return customerImpl.findAll();
	}
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable("id")int id) {
		customerImpl.deleteById(id);
	}
	@GetMapping("/findById/{id}")
	public Customer findById(@PathVariable("id")int id) {
		return customerImpl.findById(id);
	}
	@GetMapping("/findBycName/{cName}")
	public List<Customer> findBycName(@PathVariable("cName")String cName) {
		return customerImpl.findBycName(cName);
	}
	@GetMapping("/findByCity/{city}")
	public List<Customer> findByCity(@PathVariable("city") String city){
		return customerImpl.findByCity(city);
	}
	@GetMapping("/findByPincode/{pincode}")
	public List<Customer> findBypinCode(@PathVariable("pincode") int pincode){
		return customerImpl.findBypinCode(pincode);
	}
}

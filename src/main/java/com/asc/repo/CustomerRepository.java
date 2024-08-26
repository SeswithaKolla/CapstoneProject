package com.asc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asc.dto.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findById(int id);
	public List<Customer> findBycName(String cName);
	public List<Customer> findByCity(String city);
	public List<Customer> findBypinCode(int pincode);
}

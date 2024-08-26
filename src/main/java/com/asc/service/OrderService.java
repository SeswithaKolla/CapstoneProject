package com.asc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asc.dto.Order;
import com.asc.repo.OrderRepo;

@Service
public class OrderService {
	@Autowired
    private OrderRepo orderRepo;

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public Order findById(int id) {
        return orderRepo.findById(id).orElse(null);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public void deleteById(int id) {
        orderRepo.deleteById(id);
    }
    public Order findByCustomer_Id(int id) {
    	return orderRepo.findByCustomer_Id(id);
    }
}

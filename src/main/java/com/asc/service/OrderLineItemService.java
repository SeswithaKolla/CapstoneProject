package com.asc.service;

import org.springframework.stereotype.Service;

import com.asc.dto.OrderLineItem;
import com.asc.repo.OrderLineItemRepo;

@Service
public class OrderLineItemService {
	private OrderLineItemRepo linerepo;
	public OrderLineItem save(OrderLineItem orderitem){
		return linerepo.save(orderitem);
	}
	public OrderLineItem findById(int id){
		return linerepo.findById(id).orElse(null);
	}
	public void deleteById(int id){
		linerepo.deleteById(id);
	}
	public void delete(OrderLineItem orderitem){
		linerepo.delete(orderitem);
	}
}

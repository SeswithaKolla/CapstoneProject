package com.asc.repo;

import java.util.List;

import com.asc.dto.Cart;
import com.asc.dto.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class CustomOrderIpl implements CustomOrderRepo {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Order findByCustomer_Id(int id) {
		// TODO Auto-generated method stub
		String getOrderByID = "SELECT * FROM orders WHERE customer_id="+id;
		Query q = entityManager.createNativeQuery(getOrderByID,Order.class);
		List<Order> orders = q.getResultList();
		if(orders.size()==0) {
			return null;
		}
		System.out.println("----"+orders.get(0).getClass());
		Order or = (Order)orders.get(0);
		return or;
	}

}
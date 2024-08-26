package com.asc.repo;


import java.util.List;

import com.asc.dto.Cart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class CustomCartImpl implements CustomCartRepo{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Cart findByCustomer_Id(int id) {
		// TODO Auto-generated method stub
		String getCartByID = "SELECT * FROM cart WHERE customer_id="+id;
		Query q = entityManager.createNativeQuery(getCartByID,Cart.class);
		List<Cart> carts = q.getResultList();
		if(carts.size()==0) {
			return null;
		}
		System.out.println("---"+carts.get(0).getClass());
		Cart ct = (Cart)carts.get(0);
		return ct;
	}

}

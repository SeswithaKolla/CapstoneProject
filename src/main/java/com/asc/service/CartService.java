package com.asc.service;


import java.util.List;

import com.asc.dto.Cart;

public interface CartService {
	public Cart save(Cart cart);
	public Cart findByCustomer_Id(int cId);
	public void deleteById(int cartId);
	public List<Cart> findAll();
	public void delete(Cart cart);
}

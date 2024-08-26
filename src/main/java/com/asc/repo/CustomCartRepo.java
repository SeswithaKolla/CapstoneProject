package com.asc.repo;

import com.asc.dto.Cart;

public interface CustomCartRepo {
	public Cart findByCustomer_Id(int id);
}

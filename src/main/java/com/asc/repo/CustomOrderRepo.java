package com.asc.repo;

import com.asc.dto.Order;

public interface CustomOrderRepo {
	public Order findByCustomer_Id(int id);
}

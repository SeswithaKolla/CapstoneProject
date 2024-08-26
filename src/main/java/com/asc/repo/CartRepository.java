package com.asc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asc.dto.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>,CustomCartRepo {
	public Cart findByCustomer_Id(int id);
	public Cart findById(int cartId);
}

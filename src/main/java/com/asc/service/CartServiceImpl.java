package com.asc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asc.dto.Cart;
import com.asc.repo.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepository cartRepo;

	@Override
	public Cart findByCustomer_Id(int id) {
		// TODO Auto-generated method stub
		return cartRepo.findByCustomer_Id(id);
	}

	@Override
	public Cart save(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepo.save(cart);
	}

	@Override
	public void deleteById(int cartId) {
		// TODO Auto-generated method stub
		cartRepo.deleteById(cartId);
	}

	@Override
	public List<Cart> findAll() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}

	@Override
	public void delete(Cart cart) {
		// TODO Auto-generated method stub
		cartRepo.delete(cart);
	}
	


}

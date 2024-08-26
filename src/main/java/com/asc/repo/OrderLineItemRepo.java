package com.asc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asc.dto.OrderLineItem;

@Repository
public interface OrderLineItemRepo extends JpaRepository<OrderLineItem, Integer> {

}

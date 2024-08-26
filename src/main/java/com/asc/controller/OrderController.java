package com.asc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asc.dto.Customer;
import com.asc.dto.Order;
import com.asc.dto.OrderLineItem;
import com.asc.dto.OrderStatus;
import com.asc.dto.OrderView;
import com.asc.dto.Product;
import com.asc.exceptions.OrderCancellationFailedException;
import com.asc.service.CustomerService;
import com.asc.service.OrderLineItemService;
import com.asc.service.OrderService;

@RestController
@RequestMapping("/api/shop/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLineItemService lineservice;
	@Autowired
	private CustomerService customerService;
	@PostMapping("/createOrder")
	public ResponseEntity<String> createOrder(@RequestParam int customerId, @RequestBody List<Product> products) {
	    Order newOrder = new Order();
	    newOrder.getCustomer().setId(customerId);

	    List<OrderLineItem> lineItems = new ArrayList<>();
	    for (Product product : products) {
	    	OrderLineItem lineItem = new OrderLineItem();
	        lineItem.setProduct(product);
	        lineItem.setQunatity(1); 
	        lineItem.setUnitPrice(product.getPrice());
	        lineItem.setItemTotal(product.getPrice() * lineItem.getQunatity());
	        lineItem.setOrder(newOrder);
	        lineItem.setStatus(OrderStatus.PLACED);
	        lineItem.setOrderedDate(LocalDate.now());
	        lineItem.setShipmentDate(lineItem.getOrderedDate().plusDays(7));
	        lineItems.add(lineItem);
	    }

	    newOrder.setItemslist(lineItems);
	    orderService.save(newOrder);

	    return ResponseEntity.ok("Order created successfully");
	}
	@DeleteMapping("/cancelOrder")
	public ResponseEntity<String> cancelOrder(@RequestParam int orderId) {
	    Order order = orderService.findById(orderId);
	    if (order == null) {
	        throw new OrderCancellationFailedException("Order not found.");
	    }
	    boolean isAlreadyCanceled = order.getItemslist().stream()
	                                      .allMatch(item -> item.getStatus() == OrderStatus.CANCELED);
	    if (isAlreadyCanceled) {
	        throw new OrderCancellationFailedException(order);
	    }
	    order.getItemslist().forEach(item -> {
	        item.setStatus(OrderStatus.CANCELED);
	        item.setShipmentDate(null);
	    });
	    orderService.save(order);
	    return ResponseEntity.ok("Order canceled successfully");
	}

	@GetMapping("/viewOrder")
	public List<OrderView> viewOrder(@RequestParam("customerId")int customer_id){
		Order order = orderService.findByCustomer_Id(customer_id);
		if(order==null) {
			return null;
		}
		else {
			List<OrderView> orderViewList = order.getItemslist().stream()
					.map(lineitem->new OrderView(
							order.getOrderId(),
							order.getCustomer().getId(),
							lineitem.getStatus(),
							lineitem.getOrderedDate(),
							lineitem.getShipmentDate()
							))
					.collect(Collectors.toList());
			return orderViewList;
		}
	}
	@GetMapping("/findAll")
	public List<Order> findAll(){
		return orderService.findAll();
	}

}

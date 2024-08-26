package com.asc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asc.dto.Cart;
import com.asc.dto.CartView;
import com.asc.dto.LineItem;
import com.asc.dto.Order;
import com.asc.dto.OrderLineItem;
import com.asc.dto.OrderStatus;
import com.asc.dto.Product;
import com.asc.exceptions.CartNotFoundException;
import com.asc.exceptions.ProductNotFoundException;
import com.asc.repo.OrderRepo;
import com.asc.service.CartService;
import com.asc.service.LineItemService;
import com.asc.service.OrderService;

@RequestMapping("/api/shop/cart")
@RestController
public class CartController {
	    @Autowired
	    private CartService cartService;
	    @Autowired
	    private LineItemService lineService;
	    @Autowired
	    private OrderService orderService;
	    @PostMapping("/addToCart")
	    public ResponseEntity<String> addToCart(@RequestParam int customerId, @RequestBody Product product) {
	        Cart existingCart = cartService.findByCustomer_Id(customerId);
	        if (existingCart == null) {
	            Cart cart = new Cart();
	            cart.getCustomer().setId(customerId);
	            LineItem lineItem = new LineItem();
	            lineItem.setQunatity(1);
	            lineItem.setUnitPrice(product.getPrice());
	            lineItem.setItemTotal(lineItem.getQunatity() * product.getPrice());
	            lineItem.setProduct(product);
	            lineItem.setCart(cart); 
	            cart.getItemslist().add(lineItem); 
	            cartService.save(cart);
	        } 
	        else {
	            LineItem itemExisting = null;
	            for (LineItem li : existingCart.getItemslist()) {
	                if (li.getProduct().getPId() == product.getPId()) {
	                    itemExisting = li;
	                    break;
	                }
	            }
	            if (itemExisting != null) {
	                itemExisting.setQunatity(itemExisting.getQunatity() + 1);
	                itemExisting.setItemTotal(itemExisting.getItemTotal() + product.getPrice());
	            } 
	            else {
	                itemExisting = new LineItem();
	                itemExisting.setQunatity(1);
	                itemExisting.setUnitPrice(product.getPrice());
	                itemExisting.setItemTotal(product.getPrice());
	                itemExisting.setProduct(product);
	                itemExisting.setCart(existingCart);

	                existingCart.getItemslist().add(itemExisting);  
	            }

	           lineService.save(itemExisting);
	        }
			return ResponseEntity.ok("Items Added Succesfully");
	    }
	    @PutMapping("/removeFromCartByQuantity")
	    public ResponseEntity<String> removeFromCartByQuantity(@RequestParam int customerId, @RequestParam int productId) {
	       Cart existingCart = cartService.findByCustomer_Id(customerId);

	        if (existingCart != null) {
	            LineItem itemToRemove = null;
	            for (LineItem li : existingCart.getItemslist()) {
	                if (li.getProduct().getPId() == productId) {
	                    itemToRemove = li;
	                    break;
	                }
	            }

	            if (itemToRemove != null) {
	            	if (itemToRemove.getQunatity() > 1) {
	                    itemToRemove.setQunatity(itemToRemove.getQunatity() - 1);
	                    itemToRemove.setItemTotal(itemToRemove.getQunatity() * itemToRemove.getUnitPrice());
	                    lineService.save(itemToRemove);
	                    cartService.save(existingCart);
	                    return ResponseEntity.ok("Quantity decreased by 1");
	                } else {
	                    existingCart.getItemslist().remove(itemToRemove);
	                    lineService.delete(itemToRemove);
	                    if (existingCart.getItemslist().isEmpty()) {
	                        cartService.delete(existingCart);
	                        return ResponseEntity.ok("Cart removed");
	                    } else {
	                        cartService.save(existingCart);
	                        return ResponseEntity.ok("Last item removed from cart");
	                    }
	                }
	            }
	          }
	        throw new ProductNotFoundException("Product with ID " + productId + " not found in the cart");
	    }
	    @DeleteMapping("/removeFromCart")
	    public ResponseEntity<String> removeFromCart(@RequestParam int customerId, @RequestParam int productId)  {
	       Cart existingCart = cartService.findByCustomer_Id(customerId);
	        if (existingCart != null) {
	            LineItem itemToRemove = null;
	            for (LineItem li : existingCart.getItemslist()) {
	                if (li.getProduct().getPId() == productId) {
	                    itemToRemove = li;
	                    break;
	                }
	            }

	            if (itemToRemove != null) {
	                existingCart.getItemslist().remove(itemToRemove);
	                cartService.save(existingCart);
	                lineService.delete(itemToRemove);	
	                return ResponseEntity.ok("Item removed successfully");
	            }
	            else {
	                throw new ProductNotFoundException("Product with ID " + productId + " not found in the cart");
	            }
	        } 
	        throw new CartNotFoundException("Cart is empty or not found");
	    }
	    @DeleteMapping("/removeCart")
	    public ResponseEntity<String> removeCart(@RequestParam int customerId) {
	        Cart cart = cartService.findByCustomer_Id(customerId);
	        
	        if (cart != null) {
	            cartService.deleteById(cart.getCartId());
	            return ResponseEntity.ok("Cart removed successfully");
	        } else {
	            return ResponseEntity.status(404).body("Cart not found for the customer");
	        }
	    }
	    @GetMapping("/viewCart")
	    public List<CartView> viewCart(@RequestParam int customerId) {
	        Cart cartExists = cartService.findByCustomer_Id(customerId);
	        
	        if (cartExists == null) {
	            return null;
	        } else {
	            List<CartView> cartViewList = cartExists.getItemslist().stream()
	                .map(lineItem -> new CartView(
	                    cartExists.getCartId(),
	                    cartExists.getCustomer().getId(),
	                    lineItem.getLineItemId(), 
	                    lineItem.getQunatity(),
	                    lineItem.getItemTotal(),
	                    lineItem.getUnitPrice(),
	                    lineItem.getProduct().getPName()
	                ))
	                .collect(Collectors.toList());
	            
	            return cartViewList;
	        }
	    }
	    @GetMapping("/viewAllCarts")
	    public List<Cart> viewAllCarts(){
	    	return cartService.findAll();
	    }
	    @PostMapping("/placeOrder")
	    public ResponseEntity<String> placeOrder(@RequestParam int customerId, @RequestParam int productId) {
	        Cart existingCart = cartService.findByCustomer_Id(customerId);

	        if (existingCart != null && !existingCart.getItemslist().isEmpty()) {
	            LineItem cartItem = existingCart.getItemslist().stream()
	                    .filter(item -> item.getProduct().getPId() == productId)
	                    .findFirst()
	                    .orElse(null);
	            if (cartItem != null) {
	                Order order = new Order();
	                order.setCustomer(existingCart.getCustomer());
	                OrderLineItem orderLineItem = new OrderLineItem();
	                orderLineItem.setProduct(cartItem.getProduct());
	                orderLineItem.setUnitPrice(cartItem.getUnitPrice());
	                orderLineItem.setQunatity(cartItem.getQunatity());
	                orderLineItem.setItemTotal(cartItem.getItemTotal());
	                orderLineItem.setStatus(OrderStatus.PLACED);
	                orderLineItem.setOrderedDate(LocalDate.now());
	                orderLineItem.setShipmentDate(orderLineItem.getOrderedDate().plusDays(7)); 
	                orderLineItem.setOrder(order);
	                List<OrderLineItem> orderLineItems = new ArrayList<>();
	                orderLineItems.add(orderLineItem);
	                order.setItemslist(orderLineItems);
	                orderService.save(order);
	                existingCart.getItemslist().remove(cartItem);
	                if (existingCart.getItemslist().isEmpty()) {
	                    cartService.delete(existingCart); 
	                } else {
	                    cartService.save(existingCart);
	                }
	                return ResponseEntity.ok("Order placed successfully for product: " + cartItem.getProduct().getPName());
	            } else {
	                return ResponseEntity.badRequest().body("Product not found in cart");
	            }
	        } else {
	            return ResponseEntity.badRequest().body("Cart is empty or not found");
	        }
	    }
	    @PostMapping("/placeOrderNproducts")
	    public ResponseEntity<String> placeOrder(@RequestParam int customerId, @RequestBody List<Integer> productIds) {
	        Cart existingCart = cartService.findByCustomer_Id(customerId);
	        if (existingCart != null && !existingCart.getItemslist().isEmpty()) {
	            Order order = new Order();
	            order.setCustomer(existingCart.getCustomer());
	            List<OrderLineItem> orderLineItems = new ArrayList<>();
	            for (int productId : productIds) {
	                LineItem cartItem = existingCart.getItemslist().stream()
	                        .filter(item -> item.getProduct().getPId() == productId)
	                        .findFirst()
	                        .orElse(null);
	                if (cartItem != null) {
	                    OrderLineItem orderLineItem = new OrderLineItem();
	                    orderLineItem.setProduct(cartItem.getProduct());
	                    orderLineItem.setUnitPrice(cartItem.getUnitPrice());
	                    orderLineItem.setQunatity(cartItem.getQunatity());
	                    orderLineItem.setItemTotal(cartItem.getItemTotal());
	                    orderLineItem.setStatus(OrderStatus.PLACED);
	                    orderLineItem.setOrderedDate(LocalDate.now());
	                    orderLineItem.setShipmentDate(orderLineItem.getOrderedDate().plusDays(7)); 
	                    orderLineItem.setOrder(order); 
	                    orderLineItems.add(orderLineItem);
	                    existingCart.getItemslist().remove(cartItem);
	                } else {
	                    return ResponseEntity.badRequest().body("Product with ID: " + productId + " not found in cart");
	                }
	            }
	            if (!orderLineItems.isEmpty()) {
	                order.setItemslist(orderLineItems);
	                orderService.save(order);
	                if (existingCart.getItemslist().isEmpty()) {
	                    cartService.delete(existingCart);
	                } else {
	                    cartService.save(existingCart); 
	                }
	                return ResponseEntity.ok("Order placed successfully for products: " + productIds);
	            } else {
	                return ResponseEntity.badRequest().body("No valid products were found in the cart to place the order");
	            }
	        } else {
	            throw new CartNotFoundException("Cart is empty or not found");
	        }
	    }

}
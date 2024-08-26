package com.asc.exceptions;


import com.asc.dto.Order;
import com.asc.dto.OrderStatus;

public class OrderCancellationFailedException extends RuntimeException {
	

    public OrderCancellationFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public OrderCancellationFailedException(Order  order) {
        super("Order cancellation failed. Reason: " + checkOrderStatus(order));
    }
    private static String checkOrderStatus(Order order) {
        boolean isAlreadyCanceled = order.getItemslist().stream()
                                          .allMatch(item -> item.getStatus() == OrderStatus.CANCELED);
        if (isAlreadyCanceled) {
            return "Order is already canceled.";
        } else {
            return "Order could not be canceled due to unknown reasons.";
        }
    }
}

package com.asc.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter @ToString
@EqualsAndHashCode
public class CartView {
	private int cartId;
    private int customerId;
    private int lineItemId;
    private int quantity;
    private int itemTotal;
    private int unitPrice;
    private String productName;
}

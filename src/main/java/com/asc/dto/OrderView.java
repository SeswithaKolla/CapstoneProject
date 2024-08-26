package com.asc.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
public class OrderView {
	    private int orderId;
	    private int customerId;
	    private OrderStatus status;
	    private LocalDate orderedDate;
	    private LocalDate shipmentDate;
	}

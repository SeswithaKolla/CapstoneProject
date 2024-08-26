package com.asc.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
public class OrderLineItem {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int lineItemId;
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product = new Product();
	    @ManyToOne
	    @JoinColumn(name = "order_id")
	    @JsonIgnore
	    private Order order = new Order();
	    private int unitPrice;
	    private int qunatity;
	    private int itemTotal;
	    @Enumerated(EnumType.STRING)
	    private OrderStatus status;
	    private LocalDate orderedDate;
	    private LocalDate shipmentDate;
	}

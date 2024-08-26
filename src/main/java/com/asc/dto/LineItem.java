package com.asc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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
@Setter @Getter @ToString
@EqualsAndHashCode
public class LineItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lineItemId;
	@ManyToOne
	@JoinColumn(name="pId")
	private Product product = new Product();
	private int unitPrice;
	private int qunatity;
	private int itemTotal;
	@ManyToOne
	@JoinColumn(name="cart_id")
	@JsonIgnore
	private Cart cart =new Cart();
}

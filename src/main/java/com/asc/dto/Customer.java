package com.asc.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class Customer {
	@Id
	private int id;
	private String cName;
	private String email;
	private long pNo;
	private String city;
	private int pinCode;

}

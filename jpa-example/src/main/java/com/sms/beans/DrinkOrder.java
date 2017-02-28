package com.sms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drink_order")
public class DrinkOrder {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private Drink drink;

	@Column(name = "quantity")
	private Integer quantity;
	
	public DrinkOrder() {
	}

	public DrinkOrder(Drink drink, Integer quantity) {
		this.drink = drink;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

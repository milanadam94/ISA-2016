package com.sms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drink_order")
public class DrinkOrder {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Drink drink;

	@Column(name = "prepared")
	private Boolean prepared;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	public DrinkOrder() {
	}

	public DrinkOrder(Drink drink, Integer quantity) {
		this.drink = drink;
		this.quantity = quantity;
	}

	public DrinkOrder(Drink drink, Integer quantity,Boolean prepared) {
		this.drink = drink;
		this.quantity = quantity;
		this.prepared = prepared;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPrepared() {
		return prepared;
	}

	public void setPrepared(Boolean prepared) {
		this.prepared = prepared;
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

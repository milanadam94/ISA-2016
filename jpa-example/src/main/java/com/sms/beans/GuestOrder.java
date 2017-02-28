/***********************************************************************
 * Module:  Order.java
 * Author:  Sasa
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "guest_order")
public class GuestOrder implements Serializable {

	private static final long serialVersionUID = 8514991987307297478L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "prepared")
	private Boolean prepared;

	@ManyToMany
	public List<Food> foods;

	@ManyToMany
	public List<Drink> drinks;

	@ManyToOne
	public Waiter waiter;

	public GuestOrder() {
		this.drinks = new ArrayList<Drink>();
		this.foods = new ArrayList<Food>();
		this.prepared = false;
	}

	public GuestOrder(Boolean prepared, List<Food> foods, List<Drink> drinks) {
		this.prepared = prepared;
		this.foods = foods;
		this.drinks = drinks;
	}

	public Boolean getPrepared() {
		return prepared;
	}

	public void setPrepared(Boolean prepared) {
		this.prepared = prepared;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
}
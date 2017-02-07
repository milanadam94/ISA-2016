/***********************************************************************
 * Module:  Order.java
 * Author:  Sasa
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "guest_order")
public class GuestOrder implements Serializable {

	private static final long serialVersionUID = 8514991987307297478L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "prepared")
	private Boolean prepared;

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Food> foods;

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Drink> drinks;

	public GuestOrder() {
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

}
/***********************************************************************
 * Module:  Order.java
 * Author:  Sasa
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ANDREA
 *
 */
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

	@ManyToMany(cascade = CascadeType.ALL)
	public List<FoodOrder> foodOrders;

	@ManyToMany(cascade = CascadeType.ALL)
	public List<DrinkOrder> drinkOrders;

	@ManyToOne
	public Waiter waiter;

	@ManyToOne
	public Restaurant restaurant;

	public GuestOrder() {
		this.drinkOrders = new ArrayList<DrinkOrder>();
		this.foodOrders = new ArrayList<FoodOrder>();
	}

	public GuestOrder(Boolean prepared, List<FoodOrder> foodOrders, List<DrinkOrder> drinkOrders, Waiter waiter) {
		this.prepared = prepared;
		this.foodOrders = foodOrders;
		this.drinkOrders = drinkOrders;
		this.waiter = waiter;
	}

	public GuestOrder(Boolean prepared, List<FoodOrder> foodOrders, List<DrinkOrder> drinkOrders,
			Restaurant restaurant) {
		this.prepared = prepared;
		this.foodOrders = foodOrders;
		this.drinkOrders = drinkOrders;
		this.restaurant = restaurant;
	}

	public Boolean getPrepared() {
		return prepared;
	}

	public void setPrepared(Boolean prepared) {
		this.prepared = prepared;
	}

	public Boolean getDrinkOrdersPrepared(){
		for(DrinkOrder order : drinkOrders){
			if(order.getPrepared()){
				continue;
			}else{
				return false;
			}
		}	
		return true;
	}
	
	public Boolean getFoodOrdersPrepared(){
		for(FoodOrder order : foodOrders){
			if(order.getPrepared()){
				continue;
			}else{
				return false;
			}
		}
		return true;
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

	public List<FoodOrder> getFoodOrders() {
		return foodOrders;
	}

	public void setFoodOrders(List<FoodOrder> foodOrders) {
		this.foodOrders = foodOrders;
	}

	public List<DrinkOrder> getDrinkOrders() {
		return drinkOrders;
	}

	public void setDrinkOrders(List<DrinkOrder> drinkOrders) {
		this.drinkOrders = drinkOrders;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
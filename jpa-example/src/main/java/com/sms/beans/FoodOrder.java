package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "food_order")
public class FoodOrder implements Serializable {

	private static final long serialVersionUID = -1748621424889491435L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Food food;

	@Column(name = "started")
	private Boolean started;
	
	@Column(name = "prepared")
	private Boolean prepared;

	@Column(name = "quantity")
	private Integer quantity;

	public FoodOrder() {
	}

	public FoodOrder(Food food, Integer quantity) {
		this.food = food;
		this.quantity = quantity;
	}
	
	public FoodOrder(Food food, Integer quantity, Boolean started, Boolean prepared){
		this.food = food;
		this.quantity = quantity;
		this.started = started;
		this.prepared = prepared;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	public Boolean getPrepared() {
		return prepared;
	}

	public void setPrepared(Boolean prepared) {
		this.prepared = prepared;
	}
	
	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

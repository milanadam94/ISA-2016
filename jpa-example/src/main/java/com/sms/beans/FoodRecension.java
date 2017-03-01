/***********************************************************************
 * Module:  FoodRecension.java
 * Author:  Sasa
 * Purpose: Defines the Class FoodRecension
 ***********************************************************************/
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
@Table(name = "food_recension")
public class FoodRecension implements Serializable {
	
	private static final long serialVersionUID = -6420718485111801149L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "grade")
	private Integer grade;
	
	@ManyToOne
	private Guest guest;
	
	@ManyToOne
	private Food food;
	
	@ManyToOne
	private Restaurant restaurant;
	
	private String description;
	
	public FoodRecension() {
	}
	
	
	
	
	public FoodRecension(Integer grade, Guest guest, Food food,String description, Restaurant restaurant) {
		super();
		this.grade = grade;
		this.guest = guest;
		this.food = food;
		this.description = description;
		this.restaurant = restaurant;
	}




	public Guest getGuest() {
		return guest;
	}



	public void setGuest(Guest guest) {
		this.guest = guest;
	}



	public Food getFood() {
		return food;
	}



	public void setFood(Food food) {
		this.food = food;
	}



	public Restaurant getRestaurant() {
		return restaurant;
	}



	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}



	public FoodRecension(Integer grade) {
		this.grade = grade;
	}
	
	public Integer getGrade() {
		return grade;
	}
	
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
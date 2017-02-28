/***********************************************************************
 * Module:  Restaurant.java
 * Author:  Sasa
 * Purpose: Defines the Class Restaurant
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Restaurant")
public class Restaurant implements Serializable{
	
	private static final long serialVersionUID = -7680455787368156622L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "category", nullable = false)
	private String category;
	
	//@OneToOne
	//private Menu menu;
	
	
	//public ArrayList<Reservation> reservations;
	//public ArrayList<Segment> segments;
	//public ArrayList<SysUser> workrers;
	
	//public ArrayList<Offerings> offerings;
	//public ArrayList<RestaurantProfit> restaurantProfits;
	//public ArrayList<RestaurantVisitRate> restaurantVisitRates;
	//public ArrayList<RestaurantRecension> restaurantRecensions;
	
	public Restaurant() {
	}

	public Restaurant(String name, String description, String category) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

/*
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
*/

	
	

}
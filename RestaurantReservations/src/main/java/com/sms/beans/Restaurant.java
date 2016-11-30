/***********************************************************************
 * Module:  Restaurant.java
 * Author:  Sasa
 * Purpose: Defines the Class Restaurant
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class Restaurant {
	
	private String name;
	private String description;
	private String category;

	public Menu menu;
	public ArrayList<Reservation> reservations;
	public ArrayList<Segment> segments;
	public ArrayList<Worker> workrers;
	public ArrayList<RestaurantManager> restaurantManagers;
	public ArrayList<Offerer> offerers;
	public ArrayList<Tender> tenders;
	public ArrayList<Offerings> offerings;
	public ArrayList<RestaurantProfit> restaurantProfits;
	public ArrayList<RestaurantVisitRate> restaurantVisitRates;
	public ArrayList<RestaurantRecension> restaurantRecensions;
	
	public Restaurant() {
		super();
	}

	public Restaurant(String name, String description, String category) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
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
	
	

}
package com.sms.beans;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable {

	private static final long serialVersionUID = -463112696457862764L;

	private Restaurant restaurant;

	private String date;

	private List<Guest> visitors;

	public History() {
	}

	public History(Restaurant restaurant, String date, List<Guest> visitors) {
		this.restaurant = restaurant;
		this.date = date;
		this.visitors = visitors;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Guest> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Guest> visitors) {
		this.visitors = visitors;
	}

}
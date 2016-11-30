package com.sms.beans;


import java.util.*;


public class Bartender extends Worker {
	
	private ArrayList<Order> orders;
	
    public Bartender() {
		super();
	}

	public Bartender(ArrayList<Order> orders) {
		super();
		this.orders = orders;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

}
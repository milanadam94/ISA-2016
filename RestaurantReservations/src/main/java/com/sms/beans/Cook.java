/***********************************************************************
 * Module:  Cook.java
 * Author:  Sasa
 * Purpose: Defines the Class Cook
 ***********************************************************************/

package com.sms.beans;

import java.util.ArrayList;

public class Cook extends Worker {
	
   private ArrayList<Order> orders;

	public Cook() {
		super();
	}
	
	public Cook(ArrayList<Order> orders) {
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
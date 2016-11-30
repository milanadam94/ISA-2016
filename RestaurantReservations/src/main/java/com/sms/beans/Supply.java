/***********************************************************************
 * Module:  Supply.java
 * Author:  Sasa
 * Purpose: Defines the Class Supply
 ***********************************************************************/
package com.sms.beans;

public class Supply {

	private String name;
	private int price;
	private String description;
	private int quantity;

	public Supply() {
		super();
	}

	public Supply(String name, int price, String description, int quantity) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
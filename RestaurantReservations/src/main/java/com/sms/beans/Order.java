/***********************************************************************
 * Module:  Order.java
 * Author:  Sasa
 * Purpose: Defines the Class Order
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class Order {
	
	private Table table;
	private Boolean isPrepared;

	public ArrayList<Food> foods;
	public ArrayList<Drink> drinks;
	
	
	public Order() {
		super();
	}


	public Order(Table table, Boolean isPrepared) {
		super();
		this.table = table;
		this.isPrepared = isPrepared;
	}

	
	public Table getTable() {
		return table;
	}


	public void setTable(Table table) {
		this.table = table;
	}


	public Boolean getIsPrepared() {
		return isPrepared;
	}


	public void setIsPrepared(Boolean isPrepared) {
		this.isPrepared = isPrepared;
	}


	public ArrayList<Food> getFoods() {
		return foods;
	}


	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}


	public ArrayList<Drink> getDrinks() {
		return drinks;
	}


	public void setDrinks(ArrayList<Drink> drinks) {
		this.drinks = drinks;
	}

	
}
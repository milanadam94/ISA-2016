/***********************************************************************
 * Module:  Food.java
 * Author:  Sasa
 * Purpose: Defines the Class Food
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class Food {

	private String name;

	private String description;
	private int price;
	private int grade;

	private ArrayList<FoodRecension> foodRecensions;

	public Food() {
		super();
	}

	public Food(String name, String description, int price, int grade, ArrayList<FoodRecension> foodRecensions) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.grade = grade;
		this.foodRecensions = foodRecensions;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public ArrayList<FoodRecension> getFoodRecensions() {
		return foodRecensions;
	}

	public void setFoodRecensions(ArrayList<FoodRecension> foodRecensions) {
		this.foodRecensions = foodRecensions;
	}

	
}
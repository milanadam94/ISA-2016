/***********************************************************************
 * Module:  Drink.java
 * Author:  Sasa
 * Purpose: Defines the Class Drink
 ***********************************************************************/
package com.sms.beans;

public class Drink {

   private String name;
   private String description;
   private int price;
   private int grade;
   
	public Drink() {
	super();
}
	public Drink(String name, String description, int price, int grade) {
	super();
	this.name = name;
	this.description = description;
	this.price = price;
	this.grade = grade;
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
	

}
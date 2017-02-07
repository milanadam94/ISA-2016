/***********************************************************************
 * Module:  RestaurantRecension.java
 * Author:  Sasa
 * Purpose: Defines the Class RestaurantRecension
 ***********************************************************************/
package com.sms.beans;

public class RestaurantRecension {
 
	private int grade;

	public RestaurantRecension() {
		super();
	}

	public RestaurantRecension(int grade) {
		super();
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
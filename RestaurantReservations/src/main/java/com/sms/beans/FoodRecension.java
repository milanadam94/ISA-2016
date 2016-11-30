/***********************************************************************
 * Module:  FoodRecension.java
 * Author:  Sasa
 * Purpose: Defines the Class FoodRecension
 ***********************************************************************/
package com.sms.beans;

public class FoodRecension {
	
	private int grade;
	
	public FoodRecension() {
		super();
	}
	
	public FoodRecension(int grade) {
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
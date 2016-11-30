/***********************************************************************
 * Module:  RestaurantProfit.java
 * Author:  Sasa
 * Purpose: Defines the Class RestaurantProfit
 ***********************************************************************/
package com.sms.beans;

public class RestaurantProfit {
  
	private float dailyProfit;

	public RestaurantProfit() {
		super();
	}

	public RestaurantProfit(float dailyProfit) {
		super();
		this.dailyProfit = dailyProfit;
	}

	public float getDailyProfit() {
		return dailyProfit;
	}

	public void setDailyProfit(float dailyProfit) {
		this.dailyProfit = dailyProfit;
	}
	
}
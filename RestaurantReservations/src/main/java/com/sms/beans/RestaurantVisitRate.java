/***********************************************************************
 * Module:  RestaurantVisitRate.java
 * Author:  Sasa
 * Purpose: Defines the Class RestaurantVisitRate
 ***********************************************************************/
package com.sms.beans;

public class RestaurantVisitRate {

	private int dailyVisitingRate;
	private int weeklyVisitingRate;
	
	public RestaurantVisitRate() {
		super();
	}

	public RestaurantVisitRate(int dailyVisitingRate, int weeklyVisitingRate) {
		super();
		this.dailyVisitingRate = dailyVisitingRate;
		this.weeklyVisitingRate = weeklyVisitingRate;
	}

	public int getDailyVisitingRate() {
		return dailyVisitingRate;
	}

	public void setDailyVisitingRate(int dailyVisitingRate) {
		this.dailyVisitingRate = dailyVisitingRate;
	}

	public int getWeeklyVisitingRate() {
		return weeklyVisitingRate;
	}

	public void setWeeklyVisitingRate(int weeklyVisitingRate) {
		this.weeklyVisitingRate = weeklyVisitingRate;
	}

	
}
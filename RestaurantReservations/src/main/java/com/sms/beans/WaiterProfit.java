/***********************************************************************
 * Module:  WaiterProfit.java
 * Author:  Sasa
 * Purpose: Defines the Class WaiterProfit
 ***********************************************************************/
package com.sms.beans;

public class WaiterProfit {
	
    private float dailyProfit;

	public WaiterProfit() {
		super();
	}

	public WaiterProfit(float dailyProfit) {
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
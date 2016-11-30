/***********************************************************************
 * Module:  Waiter.java
 * Author:  Sasa
 * Purpose: Defines the Class Waiter
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class Waiter extends Worker {

	public ArrayList<Table> tables;
	public ArrayList<WaiterRecension> waiterRecensions;
	public ArrayList<WaiterProfit> waiterProfits;

	public Waiter() {
		super();
	}

}
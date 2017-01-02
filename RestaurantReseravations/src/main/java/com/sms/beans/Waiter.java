/***********************************************************************
 * Module:  Waiter.java
 * Author:  Sasa
 * Purpose: Defines the Class Waiter
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Waiter extends Worker implements Serializable {

	private static final long serialVersionUID = 7247520519592208669L;
	
	public ArrayList<GuestTable> tables;
	public ArrayList<WaiterRecension> waiterRecensions;
	public ArrayList<WaiterProfit> waiterProfits;

	public Waiter() {
		super();
	}

}
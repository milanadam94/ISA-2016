/***********************************************************************
 * Module:  Visit.java
 * Author:  Sasa
 * Purpose: Defines the Class Visit
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;
import java.util.Date;

public class Visit {
	
	private Date date;

	public Restaurant restaurant;
	public Guest guest;

	public ArrayList<Guest> friends;

	public Visit() {
		super();
	}

	public Visit(Date date) {
		super();
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
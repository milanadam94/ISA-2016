/***********************************************************************
 * Module:  History.java
 * Author:  Sasa
 * Purpose: Defines the Class History
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class History {
	
    private ArrayList<Visit> visits;

	public History() {
		super();
	}

	public History(ArrayList<Visit> visits) {
		super();
		this.visits = visits;
	}

	public ArrayList<Visit> getVisits() {
		return visits;
	}

	public void setVisits(ArrayList<Visit> visits) {
		this.visits = visits;
	}
    
}
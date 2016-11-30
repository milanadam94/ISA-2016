/***********************************************************************
 * Module:  Segment.java
 * Author:  Sasa
 * Purpose: Defines the Class Segment
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class Segment {

	private ArrayList<Table> tables;

	public Segment() {
		super();
	}

	public Segment(ArrayList<Table> tables) {
		super();
		this.tables = tables;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}
	
}
/***********************************************************************
 * Module:  Table.java
 * Author:  Sasa
 * Purpose: Defines the Class Table
 ***********************************************************************/
package com.sms.beans;

public class Table {

	private int seatCount;
	private String description;
	private boolean smoking;
	
	public Table() {
		super();
	}

	public Table(int seatCount, String description, boolean smoking) {
		super();
		this.seatCount = seatCount;
		this.description = description;
		this.smoking = smoking;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	
}
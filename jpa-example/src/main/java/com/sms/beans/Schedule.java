/***********************************************************************
 * Module:  Schedule.java
 * Author:  Sasa
 * Purpose: Defines the Class Schedule
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;
import java.util.Date;

public class Schedule {
	
	private Date startDate;
	private Date endDate;
	private String shift;

	public ArrayList<Worker> workers;

	public Schedule() {
		super();
	}

	public Schedule(Date startDate, Date endDate, String shift) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.shift = shift;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
	
	
}
/***********************************************************************
 * Module:  Reservation.java
 * Author:  Sasa
 * Purpose: Defines the Class Reservation
 ***********************************************************************/
package com.sms.beans;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
	
	private Date dateAndTime;
	private Time duration;

	public ArrayList<Order> orders;
	public ArrayList<Table> tables;
	
	public Reservation() {
		super();
	}

	public Reservation(Date dateAndTime, Time duration) {
		super();
		this.dateAndTime = dateAndTime;
		this.duration = duration;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}
	
	
}
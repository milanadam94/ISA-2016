/***********************************************************************
 * Module:  Event.java
 * Author:  Sasa
 * Purpose: Defines the Class Event
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class Event {
	
    private Reservation reservation;
    private ArrayList<Guest> acceptedGuests;
    
    public Event() {
		super();
	}
	
	public Event(Reservation reservation, ArrayList<Guest> acceptedGuests) {
		super();
		this.reservation = reservation;
		this.acceptedGuests = acceptedGuests;
	}
    public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public ArrayList<Guest> getAcceptedGuests() {
		return acceptedGuests;
	}
	public void setAcceptedGuests(ArrayList<Guest> acceptedGuests) {
		this.acceptedGuests = acceptedGuests;
	}

}
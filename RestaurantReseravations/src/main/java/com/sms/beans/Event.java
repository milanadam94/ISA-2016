/***********************************************************************
 * Module:  Event.java
 * Author:  Sasa
 * Purpose: Defines the Class Event
 ***********************************************************************/
package com.sms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Event() {
	}
	
	public Event(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
//    private Reservation reservation;
//    private ArrayList<Guest> acceptedGuests;
//    
//    public Event() {
//		super();
//	}
//	
//	public Event(Reservation reservation, ArrayList<Guest> acceptedGuests) {
//		super();
//		this.reservation = reservation;
//		this.acceptedGuests = acceptedGuests;
//	}
//    public Reservation getReservation() {
//		return reservation;
//	}
//	public void setReservation(Reservation reservation) {
//		this.reservation = reservation;
//	}
//	public ArrayList<Guest> getAcceptedGuests() {
//		return acceptedGuests;
//	}
//	public void setAcceptedGuests(ArrayList<Guest> acceptedGuests) {
//		this.acceptedGuests = acceptedGuests;
//	}

}
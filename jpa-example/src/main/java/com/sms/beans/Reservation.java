/***********************************************************************
 * Module:  Reservation.java
 * Author:  Sasa
 * Purpose: Defines the Class Reservation
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable {

	private static final long serialVersionUID = -1768759508918044208L;


	@Column(name = "reservation_date_time")
	private Date reservationDateTime;

	@Column(name = "duration")
	private Integer duration;

	@ManyToOne
	private Guest guest;

	@ManyToOne
	private Restaurant restaurant;

	@OneToMany
	private ArrayList<GuestTable> tables;

	public Reservation() {
	}

	public Reservation(Date reservationDateTime, Integer duration, Guest guest, Restaurant restaurant,
			ArrayList<GuestTable> tables) {
		super();
		this.reservationDateTime = reservationDateTime;
		this.duration = duration;
		this.guest = guest;
		this.restaurant = restaurant;
		this.tables = tables;
	}

	public Date getReservationDateTime() {
		return reservationDateTime;
	}

	public void setReservationDateTime(Date reservationDateTime) {
		this.reservationDateTime = reservationDateTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public ArrayList<GuestTable> getTables() {
		return tables;
	}

	public void setTables(ArrayList<GuestTable> tables) {
		this.tables = tables;
	}

}
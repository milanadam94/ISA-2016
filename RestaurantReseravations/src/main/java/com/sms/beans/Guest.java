/***********************************************************************
 * Module:  Guest.java
 * Author:  Sasa
 * Purpose: Defines the Class Guest
 ***********************************************************************/
package com.sms.beans;

import java.util.ArrayList;

public class Guest {
	
	private Integer id;
	
	private String email;
	private String password;
	private String name;
	private String lastname;
	private String address;

	public ArrayList<Friendship> friendships;
	public ArrayList<FriendRequest> requests;
	public ArrayList<Reservation> guestReservations;
	public ArrayList<Event> createdEvents;
	public ArrayList<AcceptedEvent> asseptedEvents;
	public ArrayList<GuestOrder> orders;
	
	public RestaurantRecension restaurantRecension;
	public FoodRecension foodRecensions;
	public WaiterRecension waiterRecension;
	public History history;
	
	public Guest() {
		super();
	}

	public Guest(String email, String password, String name, String lastname, String address) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
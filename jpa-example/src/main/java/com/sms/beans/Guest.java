/***********************************************************************
 * Module:  Guest.java
 * Author:  Sasa
 * Purpose: Defines the Class Guest
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "guest")
public class Guest implements Serializable{
	
	private static final long serialVersionUID = -5598139396395552585L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "adress")
	private String address;

	/*
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
	*/
	
	public Guest() {
	}

	public Guest(String email, String password, String name, String lastName, String address) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
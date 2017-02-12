/***********************************************************************
 * Module:  Guest.java
 * Author:  Sasa
 * Purpose: Defines the Class Guest
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Guest")
public class Guest implements Serializable {

	private static final long serialVersionUID = -5598139396395552585L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	private SysUser user;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) public
	 * ArrayList<Friendship> friendships; public ArrayList<FriendRequest>
	 * requests; public ArrayList<Reservation> guestReservations; public
	 * ArrayList<Event> createdEvents; public ArrayList<AcceptedEvent>
	 * asseptedEvents; public ArrayList<GuestOrder> orders;
	 * 
	 * public RestaurantRecension restaurantRecension; public FoodRecension
	 * foodRecensions; public WaiterRecension waiterRecension; public History
	 * history;
	 */

	public Guest() {
	}

	public Guest(SysUser user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

}
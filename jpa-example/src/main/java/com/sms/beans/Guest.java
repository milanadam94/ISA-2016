/***********************************************************************
 * Module:  Guest.java
 * Author:  Sasa
 * Purpose: Defines the Class Guest
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Guest")
public class Guest implements Serializable {

	private static final long serialVersionUID = -5598139396395552585L;

	@Transient
	private List<Guest> friends;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private SysUser user;

	@ManyToMany
	private Set<Guest> friendRequests;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "visits")
	private Integer visits;

	public Guest() {

	}

	public Guest(SysUser user, Integer visits) {
		this.user = user;
		this.visits = visits;
	}

	public Guest(SysUser user, String address, String city, Integer visits) {
		super();
		this.user = user;
		this.address = address;
		this.city = city;
		this.visits = visits;
	}

	public void addFriend(Guest friend) {
		this.friends.add(friend);
	}

	public void removeFriend(Guest friend) {
		this.friends.remove(friend);
	}

	public void addFriendRequest(Guest request) {
		this.friendRequests.add(request);
	}

	public void removeFriendRequest(Guest request) {
		this.friendRequests.remove(request);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Set<Guest> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequests(Set<Guest> friendRequests) {
		this.friendRequests = friendRequests;
	}

	public List<Guest> getFriends() {
		return friends;
	}

	public void setFriends(List<Guest> friends) {
		this.friends = friends;
	}

}
/***********************************************************************
 * Module:  Guest.java
 * Author:  Sasa
 * Purpose: Defines the Class Guest
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

	@ManyToOne
	private SysUser user;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Guest> friends;

	public Guest() {

	}

	public void addFriend(Guest friend) {
		this.friends.add(friend);
	}

	public List<Guest> getFriends() {
		return friends;
	}

	public void setFriends(List<Guest> friends) {
		this.friends = friends;
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
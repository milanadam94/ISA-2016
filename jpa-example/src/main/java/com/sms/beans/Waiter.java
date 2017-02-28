/***********************************************************************
 * Module:  Waiter.java
 * Author:  Sasa
 * Purpose: Defines the Class Waiter
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Waiter")
public class Waiter implements Serializable {

	private static final long serialVersionUID = 7247520519592208669L;

	// public ArrayList<GuestTable> tables;
	// public ArrayList<WaiterRecension> waiterRecensions;
	// public ArrayList<WaiterProfit> waiterProfits;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@Column(name = "suitSize")
	private String suitSize;
	
	@Column(name = "shoeSize")
	private int shoeSize;
	
	@Column(name = "birthday")
	private String birthday;

	@ManyToOne
	private SysUser user;

	@ManyToOne
	private Restaurant restaurant;
	
	
	public Waiter() {
	
	}
	
	

	public Waiter(SysUser user){
		this.user = user;
	}
	
	public Waiter(SysUser user, Restaurant restourant) {
		this.user = user;
		this.restaurant = restourant;
	}
	
	public Waiter(String email, String password, String suitSize, int shoeSize, String birthday, SysUser user) {
		this.user = user;
		this.suitSize = suitSize;
		this.shoeSize = shoeSize;
		this.birthday = birthday;
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

	public String getSuitSize() {
		return suitSize;
	}

	public void setSuitSize(String suitSize) {
		this.suitSize = suitSize;
	}

	public int getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(int shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
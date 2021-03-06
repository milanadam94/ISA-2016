/***********************************************************************
 * Module:  RestaurantManager.java
 * Author:  Sasa
 * Purpose: Defines the Class RestaurantManager
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Restaurant_Manager")
public class RestaurantManager implements Serializable{

	private static final long serialVersionUID = 6116878816679029187L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne
	private SysUser user;
	
	@ManyToOne
	private Restaurant restaurant;
	
	
	public RestaurantManager() {
	}

	public RestaurantManager(SysUser user) {
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	

	
}
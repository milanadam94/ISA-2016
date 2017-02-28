/***********************************************************************
 * Module:  Cook.java
 * Author:  Sasa
 * Purpose: Defines the Class Cook
 ***********************************************************************/

package com.sms.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cook")
public class Cook implements Serializable {

	private static final long serialVersionUID = -5494704852933484654L;

	@Id
	@Column(name = "id")
	@GeneratedValue
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
	
	@Column(name = "cook_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private FoodType cookType;
	
	public Cook() {
	}

	public Cook(SysUser user) {
		this.user = user;
	}

	public Cook(SysUser user, Restaurant restourant) {
		this.user = user;
		this.restaurant = restourant;
	}

	public Cook(String suitSize, int shoeSize, String birthday, SysUser user, FoodType cookType){
		this.user = user;
		this.suitSize = suitSize;
		this.shoeSize = shoeSize;
		this.birthday = birthday;
		this.cookType = cookType;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public FoodType getCookType() {
		return cookType;
	}

	public void setCookType(FoodType cookType) {
		this.cookType = cookType;
	}

	
}
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
@Table(name = "Bartender")
public class Bartender implements Serializable{
	
	private static final long serialVersionUID = -293897601884272776L;

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
	
	
    public Bartender() {
	}
    
	public Bartender(SysUser user) {
		this.user = user;
	}

	public Bartender(SysUser user, Restaurant restourant) {
		this.user = user;
		this.restaurant = restourant;
	}
	
	public Bartender(String suitSize, int shoeSize, String birthday, SysUser user){
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
	
	/*public List<GuestOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GuestOrder> orders) {
		this.orders = orders;
	}*/

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
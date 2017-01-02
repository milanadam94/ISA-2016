package com.sms.beans;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bartender")
public class Bartender extends Worker implements Serializable{
	
	private static final long serialVersionUID = -293897601884272776L;

	@OneToMany(cascade = CascadeType.ALL)
	private List<GuestOrder> orders;
	
    public Bartender() {
	}

	public Bartender(List<GuestOrder> orders) {
		this.orders = orders;
	}

	public List<GuestOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GuestOrder> orders) {
		this.orders = orders;
	}

}
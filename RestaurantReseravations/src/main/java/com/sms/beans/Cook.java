/***********************************************************************
 * Module:  Cook.java
 * Author:  Sasa
 * Purpose: Defines the Class Cook
 ***********************************************************************/

package com.sms.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cook")
public class Cook extends Worker implements Serializable {
	
	private static final long serialVersionUID = -5494704852933484654L;

	@ManyToMany(cascade = CascadeType.ALL)
    private List<GuestOrder> orders;

	public Cook() {
	}
	
	public Cook(List<GuestOrder> orders) {
		this.orders = orders;
	}
	
	public List<GuestOrder> getOrders() {
		return orders;
	}
	
	public void setOrders(List<GuestOrder> orders) {
		this.orders = orders;
	}
   

}
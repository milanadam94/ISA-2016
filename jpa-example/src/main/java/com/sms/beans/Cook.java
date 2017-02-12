/***********************************************************************
 * Module:  Cook.java
 * Author:  Sasa
 * Purpose: Defines the Class Cook
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
@Table(name = "Cook")
public class Cook implements Serializable {

	private static final long serialVersionUID = -5494704852933484654L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	private SysUser user;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<GuestOrder> orders;

	public Cook() {
	}

	public Cook(SysUser user) {
		this.user = user;
	}

	public List<GuestOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GuestOrder> orders) {
		this.orders = orders;
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
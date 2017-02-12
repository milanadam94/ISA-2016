/***********************************************************************
 * Module:  Waiter.java
 * Author:  Sasa
 * Purpose: Defines the Class Waiter
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

	@ManyToOne(cascade = CascadeType.ALL)
	private SysUser user;

	public Waiter() {
	}

	public Waiter(String email, String password, SysUser user) {
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
/***********************************************************************
 * Module:  SystemManager.java
 * Author:  Sasa
 * Purpose: Defines the Class SystemManager
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
@Table(name = "System_Manager")
public class SystemManager implements Serializable {

	private static final long serialVersionUID = -6185735289382550891L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	private SysUser user;

	// public ArrayList<Restaurant> restaurants;

	public SystemManager() {
	}

	public SystemManager(SysUser user) {
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
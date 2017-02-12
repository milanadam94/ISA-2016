/***********************************************************************
 * Module:  Offerer.java
 * Author:  Sasa
 * Purpose: Defines the Class Offerer
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
@Table(name = "Offerer")
public class Offerer implements Serializable{

	private static final long serialVersionUID = 8785063526775850679L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	private SysUser user;

	// public ArrayList<Offerings> offerings;
	// public ArrayList<Supply> supplies;
	public Offerer() {
	}

	public Offerer(SysUser user) {
		this.user = user;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
/***********************************************************************
 * Module:  WaiterRecension.java
 * Author:  Sasa
 * Purpose: Defines the Class WaiterRecension
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
@Table(name = "waiter_recension")
public class WaiterRecension implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1674355958961489706L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private int grade;

	@ManyToOne
	private Waiter waiter;
	
	@ManyToOne
	private Guest guest;
	
	private String description;
	
	
	public WaiterRecension() {
		super();
	}
	
	public WaiterRecension(int grade, Waiter waiter, Guest guest, String description) {
		super();
		this.grade = grade;
		this.waiter = waiter;
		this.guest = guest;
		this.description = description;
	}

	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public Waiter getWaiter() {
		return waiter;
	}


	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}


	public Guest getGuest() {
		return guest;
	}


	public void setGuest(Guest guest) {
		this.guest = guest;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	
	
}
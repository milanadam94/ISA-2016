/***********************************************************************
 * Module:  Table.java
 * Author:  Sasa
 * Purpose: Defines the Class Table
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
import javax.persistence.Table;

@Entity
@Table(name = "guest_table")
public class GuestTable implements Serializable {

	private static final long serialVersionUID = 515162871579073420L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "seat_count")
	private Integer seatCount;

	@Column(name = "description")
	private String description;

	@Column(name = "smoking")
	private Boolean smoking;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<GuestOrder> orders;

	public GuestTable() {
	}

	public GuestTable(Integer seatCount, String description, Boolean smoking, List<GuestOrder> orders) {
		this.seatCount = seatCount;
		this.description = description;
		this.smoking = smoking;
		this.orders = orders;
	}

	public List<GuestOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GuestOrder> orders) {
		this.orders = orders;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getSmoking() {
		return smoking;
	}

	public void setSmoking(Boolean smoking) {
		this.smoking = smoking;
	}

}
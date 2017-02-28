/***********************************************************************
 * Module:  Table.java
 * Author:  Sasa
 * Purpose: Defines the Class Table
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Guest_Table")
public class GuestTable implements Serializable {

	private static final long serialVersionUID = 515162871579073420L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "seat_count")
	private Integer seatCount;

	@Column(name = "description")
	private String description;
	
	@Column(name = "xCoord")
	private Double xCoord;
	
	@Column(name = "yCoord")
	private Double yCoord;

	//@ManyToOne
	//private Segment segment;

	@ManyToOne(fetch = FetchType.LAZY)
	private Restaurant restaurant;

	public GuestTable() {
	}

	public GuestTable(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public GuestTable(Integer seatCount, String description) {
		this.seatCount = seatCount;
		this.description = description;
	}

	public GuestTable(Integer seatCount, Restaurant restaurant) {
		this.seatCount = seatCount;
		this.restaurant = restaurant;
	}

	public GuestTable(Integer seatCount, String description, Restaurant restaurant) {
		this.seatCount = seatCount;
		this.description = description;
		this.restaurant = restaurant;
	}

	

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public Double getxCoord() {
		return xCoord;
	}

	public void setxCoord(Double xCoord) {
		this.xCoord = xCoord;
	}

	public Double getyCoord() {
		return yCoord;
	}

	public void setyCoord(Double yCoord) {
		this.yCoord = yCoord;
	}
	
	

}
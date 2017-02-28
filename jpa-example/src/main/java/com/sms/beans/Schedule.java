/***********************************************************************
 * Module:  Schedule.java
 * Author:  Sasa
 * Purpose: Defines the Class Schedule
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3140241713399384804L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date startDate;
	
	private Date endDate;
	
	private ShiftType shift;
	
	@ManyToOne
	private Segment segment;

	public Schedule() {
		super();
	}
	
	public Schedule(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.setEndDate(endDate);
	}
	
	public Schedule(Date startDate, Date endDate, ShiftType shift) {
		super();
		this.startDate = startDate;
		this.setEndDate(endDate);
		this.shift = shift;
	}
	


	public Schedule(Date startDate, Date endDate, ShiftType shift, Segment segment) {
		super();
		this.startDate = startDate;
		this.setEndDate(endDate);
		this.shift = shift;
		this.segment = segment;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public ShiftType getShift() {
		return shift;
	}

	public void setShift(ShiftType shift) {
		this.shift = shift;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
	
	
}